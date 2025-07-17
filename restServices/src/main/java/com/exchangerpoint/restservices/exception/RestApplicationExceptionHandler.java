package com.exchangerpoint.restservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.exchangerpoint.commonservices.constant.ApplicationConstants;
import com.exchangerpoint.commonservices.errorcode.RestErrorCode;
import com.exchangerpoint.commonservices.exception.RestException;
import com.exchangerpoint.restservices.bean.RestError;

@ControllerAdvice
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(RestException.class)
	public ResponseEntity<RestError> handleRestException(RestException e) 
	{	
		RestError restError = new RestError(e.getErrorCode(),e.getErrorMessage(),HttpStatus.CONFLICT.value(),LocalDateTime.now());
	return new ResponseEntity<RestError>(restError, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<RestError> handleInvalidTokenException(InvalidTokenException e) 
	{	
		RestError restError = new RestError(RestErrorCode.INVALID_TOKEN.getErrorCode(),RestErrorCode.INVALID_TOKEN.getErrorMessage(),HttpStatus.UNAUTHORIZED.value(),LocalDateTime.now());
	return new ResponseEntity<RestError>(restError, HttpStatus.UNAUTHORIZED);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) 
	{	
		RestError restError = new RestError(RestErrorCode.BAD_REQUEST.getErrorCode(),RestErrorCode.BAD_REQUEST.getErrorMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
	    return handleExceptionInternal(e, restError, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) 
	{	
		RestError restError = new RestError(RestErrorCode.METHOD_NOT_SUPPORTED.getErrorCode(),RestErrorCode.METHOD_NOT_SUPPORTED.getErrorMessage(),HttpStatus.METHOD_NOT_ALLOWED.value(),LocalDateTime.now()); 
		return handleExceptionInternal(e, restError, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}
	
	
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			HttpMediaTypeNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request)
	{	
		RestError restError = new RestError(RestErrorCode.CONTENT_TYPE_NOT_SUPPORTED.getErrorCode(),RestErrorCode.CONTENT_TYPE_NOT_SUPPORTED.getErrorMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),LocalDateTime.now());
		return handleExceptionInternal(e, restError, headers, HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}
	
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
			HttpMediaTypeNotAcceptableException e, HttpHeaders headers, HttpStatus status, WebRequest request)
	{	
		RestError restError = new RestError(RestErrorCode.CONTENT_TYPE_NOT_SUPPORTED.getErrorCode(),RestErrorCode.CONTENT_TYPE_NOT_SUPPORTED.getErrorMessage(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),LocalDateTime.now());
		return handleExceptionInternal(e, restError, headers, HttpStatus.NOT_ACCEPTABLE, request);
	}
	
	@Override
	public ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException e, HttpHeaders headers, HttpStatus status, WebRequest request)
	{	
		RestError restError = new RestError(RestErrorCode.METHOD_NOT_SUPPORTED.getErrorCode(),RestErrorCode.METHOD_NOT_SUPPORTED.getErrorMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
		return handleExceptionInternal(e, restError, headers, HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<RestError> handleRuntimeException(RuntimeException e) 
	{	RestError restError = null;
	
		if(e.getMessage().equals(ApplicationConstants.ACCESS_DENIED))
		 restError = new RestError(RestErrorCode.ACCESS_DENIED.getErrorCode() , RestErrorCode.ACCESS_DENIED.getErrorMessage());
		
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_INACTIVE))
		 restError = new RestError(RestErrorCode.USER_ACCOUNT_NOT_ACTIVE.getErrorCode() , RestErrorCode.USER_ACCOUNT_NOT_ACTIVE.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_BLOCKED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_BLOCKED.getErrorCode() , RestErrorCode.USER_ACCOUNT_BLOCKED.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_SUSPENDED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_SUSPENDED.getErrorCode() , RestErrorCode.USER_ACCOUNT_SUSPENDED.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_CLOSED))
			 restError = new RestError(RestErrorCode.USER_ACCOUNT_CLOSED.getErrorCode() , RestErrorCode.USER_ACCOUNT_CLOSED.getErrorMessage());
		
		
		if(e.getMessage().equals(ApplicationConstants.ACCOUNT_STATUS_FAILED_ATTEMPT))
			 restError = new RestError(RestErrorCode.USER_MAX_FAILED_ATTEMPT.getErrorCode() , RestErrorCode.USER_MAX_FAILED_ATTEMPT.getErrorMessage());
		
		if(e.getMessage().equals(ApplicationConstants.LAST_LOGIN_IP_NOT_MATCH))
			 restError = new RestError(RestErrorCode.LAST_LOGIN_IP_NOT_MATCH.getErrorCode() , RestErrorCode.LAST_LOGIN_IP_NOT_MATCH.getErrorMessage());
		
		return new ResponseEntity<RestError>(restError, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({AuthenticationException.class, UsernameNotFoundException.class})
	public ResponseEntity<RestError> handleAuthenticationException(AuthenticationException e) 
	{	RestError restError =  new RestError(RestErrorCode.BAD_CREDENTIAL.getErrorCode() , RestErrorCode.BAD_CREDENTIAL.getErrorMessage(),HttpStatus.UNAUTHORIZED.value(),LocalDateTime.now());
	
		return new ResponseEntity<RestError>(restError, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestError> handleAll(Exception e) 
	{	
		RestError restError = new RestError(RestErrorCode.INTERNAL_SERVER_ERROR.getErrorCode(),RestErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
	return new ResponseEntity<RestError>(restError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
