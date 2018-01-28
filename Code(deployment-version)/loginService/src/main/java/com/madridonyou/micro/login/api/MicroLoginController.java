package com.madridonyou.micro.login.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.madridonyou.awsUtils.dynamoDB.DynamoDBUtils;
import com.madridonyou.micro.domain.dynamoTables.MoyUsers;
import com.madridonyou.micro.domain.dynamoTables.User;
import com.madridonyou.micro.domain.inputs.LoginInput;
import com.madridonyou.micro.domain.inputs.RegisterInput;
import com.madridonyou.micro.domain.outputs.LoginOutput;
import com.madridonyou.micro.domain.outputs.RegisterOutput;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

@Controller
public class MicroLoginController implements MicroLoginAPI{

	private DynamoDBUtils utils = new DynamoDBUtils();
	
	public ResponseEntity<LoginOutput> login(@Valid @RequestBody LoginInput input) {

		LoginOutput responseBody = new LoginOutput();
		HttpStatus responseStatus = HttpStatus.OK;

		try {
			if (checkInput(input))
			{
				Map<String,AttributeValue> key = new HashMap<String,AttributeValue>();
				key.put(MoyUsers.USER_ID.getField(), AttributeValue.builder().s(""+input.getUsername().hashCode()).build());

				GetItemResponse item = utils.getFrom(MoyUsers.TABLENAME.getField(), key);
				if (item != null && item.item() != null 
						&& !item.item().isEmpty() 
						&& item.item().get("password").equals(AttributeValue.builder().s(""+input.getPassword().hashCode()).build()))
				{
					responseStatus = HttpStatus.OK;
					responseBody.setStatus("Login OK");
					responseBody.setEmail(item.item().get("email").s());
				}
				else responseBody.setStatus("Credenciales incorrectas");
			}
			else responseBody.setStatus("Credenciales incorrectas");
		}
		catch (Exception e) {
			responseBody.setStatus(e.getMessage());
		}
		return new ResponseEntity<LoginOutput>(responseBody,responseStatus);
	}

	public ResponseEntity<RegisterOutput> register(@Valid @RequestBody RegisterInput input) {

		HttpStatus responseStatus = HttpStatus.OK;
		RegisterOutput responseBody = new RegisterOutput();

		try {
			if (checkInput(input))
			{
				Map<String,AttributeValue> newUser = new HashMap<String,AttributeValue>();
				newUser.put(MoyUsers.USERNAME.getField(), AttributeValue.builder().s(input.getUsername()).build());
				newUser.put(MoyUsers.PASSWORD.getField(), AttributeValue.builder().s(""+input.getPassword().hashCode()).build());
				newUser.put(MoyUsers.NAME.getField(), AttributeValue.builder().s(input.getUsername()).build());
				newUser.put(MoyUsers.USER_ID.getField(), AttributeValue.builder().s(""+input.getUsername().hashCode()).build());
				newUser.put(MoyUsers.EMAIL.getField(), AttributeValue.builder().s(""+input.getMail()).build());
				utils.putToWithCondition(MoyUsers.TABLENAME.getField(), "attribute_not_exists(userId)", newUser);
				
				responseStatus = HttpStatus.OK;
				responseBody.setStatus("Register OK");
			}
			else responseBody.setStatus("Error en los campos de entrada");
		} 
		catch (ConditionalCheckFailedException e) {
			responseBody.setStatus("Ya existe ese nombre de usuario");
		}
		catch (Exception e) {
			responseBody.setStatus(e.getMessage());
		}
		return new ResponseEntity<RegisterOutput>(responseBody, responseStatus);
	}

	private boolean checkInput(Object input) {

		if (input != null)
		{
			boolean correct = true;
			if (input instanceof LoginInput)
			{
				Iterator<String> props = ((LoginInput) input).getProperties();
				while (props.hasNext() && correct)
				{
					String prop = props.next();
					correct = prop != null && !prop.equals("") && prop.length() > 2;
				}
			}
			else if (input instanceof RegisterInput)
			{
				Iterator<String> props = ((RegisterInput) input).getProperties();
				while (props.hasNext() && correct)
				{
					String prop = props.next();
					correct = prop != null && !prop.equals("") && prop.length() > 2;
				}
			}
			return correct;
		}
		else return false;
	}

	@Override
	public ResponseEntity<RegisterOutput> update(@Valid @RequestBody RegisterInput input) {
		
		HttpStatus responseStatus = HttpStatus.OK;
		RegisterOutput responseBody = new RegisterOutput();

		try {
			if (checkInput(input))
			{
				Map<String,AttributeValue> key = new HashMap<String,AttributeValue>();
				Map<String,AttributeValueUpdate> attributeUpdates = new HashMap<String,AttributeValueUpdate>();
				
				key.put(MoyUsers.USER_ID.getField(), AttributeValue.builder().s(""+input.getUsername().hashCode()).build());
				
				attributeUpdates.put(MoyUsers.EMAIL.getField(), AttributeValueUpdate.builder().value(AttributeValue.builder().s(input.getMail()).build()).build());
				attributeUpdates.put(MoyUsers.PASSWORD.getField(), AttributeValueUpdate.builder().value(AttributeValue.builder().s(""+input.getPassword().hashCode()).build()).build());

				utils.updateItemFrom(MoyUsers.TABLENAME.getField(), key, attributeUpdates);
				
				responseStatus = HttpStatus.OK;
				responseBody.setStatus("Update OK");
			}
			else responseBody.setStatus("Error en los campos de entrada");
		} 
		catch (ConditionalCheckFailedException e) {
			responseBody.setStatus("Ya existe ese nombre de usuario");
		}
		catch (Exception e) {
			responseBody.setStatus(e.getMessage());
		}
		return new ResponseEntity<RegisterOutput>(responseBody, responseStatus);
	}

	@Override
	public ResponseEntity<User> getUserInfo(@Valid @PathVariable("username") String username) {
		Map<String,AttributeValue> key = new HashMap<String,AttributeValue>();
		key.put(MoyUsers.USER_ID.getField(), AttributeValue.builder().s(""+username.hashCode()).build());
		GetItemResponse user = utils.getFrom(MoyUsers.TABLENAME.getField(), key);
		User userReturned = new User();
		userReturned.setEmail(user.item().get("email").s());
		userReturned.setUsername(user.item().get("username").s());
		return ResponseEntity.ok(userReturned);
	}

}
