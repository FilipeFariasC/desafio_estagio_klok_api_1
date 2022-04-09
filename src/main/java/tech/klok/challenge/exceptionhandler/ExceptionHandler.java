package tech.klok.challenge.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//define a mensagem que será enviada em caso de erro de conversao de entidade
		
		List<Error> errors = Arrays.asList(
				new Error(
						messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale()),
						exception.getCause().toString()));
		
		return handleExceptionInternal(exception, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> errors = createErrorList(exception.getBindingResult());
		
		return handleExceptionInternal(exception, errors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Error> createErrorList(BindingResult bindingResult){
		return bindingResult.getFieldErrors()
			.stream()
			.map((field) ->
				new Error(
					messageSource.getMessage(field, LocaleContextHolder.getLocale()), // localiza a mensagem para o usuário
					field.toString()) // stacktrace como mensagem para o desenvolvedor
			).toList();
	}
	/**
	 *	Classe que encapsula as mensagens de erro que serão enviadas
	 * @author Filipe Farias Chagas
	 */
	private class Error {
		
		private String messageUser;
		private String messageDeveloper;
		
		public Error (String messageUser, String messageDeveloper){
			this.messageDeveloper = messageDeveloper;
			this.messageUser = messageUser;
		}

		public String getMessageUser() {
			return messageUser;
		}
		public void setMessageUser(String messageUser) {
			this.messageUser = messageUser;
		}

		public String getMessageDeveloper() {
			return messageDeveloper;
		}
		public void setMessageDeveloper(String messageDeveloper) {
			this.messageDeveloper = messageDeveloper;
		}
	}
}
