package com.madridonyou.awsUtils.dynamoDB;

import java.util.List;
import java.util.Map;

import software.amazon.awssdk.auth.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemResponse;


public class DynamoDBUtils {

	private DynamoDBClient dynamoClient = DynamoDBClient.builder()
			.credentialsProvider(new ProfileCredentialsProvider())
			.region(Region.EU_WEST_1).build();
	
	public PutItemResponse putToWithCondition (String tablename, String condition, Map<String,AttributeValue> item) {
		return dynamoClient.putItem(PutItemRequest.builder()
				.tableName(tablename)
				.conditionExpression(condition)
				.item(item)
				.build());
	}
	
	public PutItemResponse putTo (String tablename, Map<String,AttributeValue> item) {
		return dynamoClient.putItem(PutItemRequest.builder()
				.tableName(tablename)
				.item(item)
				.build());
	}
	
	public GetItemResponse getFrom (String tablename, Map<String,AttributeValue> key, List<String> attrToGet) {
		return dynamoClient.getItem(GetItemRequest.builder()
				.tableName(tablename)
				.key(key)
				.attributesToGet(attrToGet)
				.build());
	}
	
	public GetItemResponse getFrom (String tablename, Map<String,AttributeValue> key) {
		return dynamoClient.getItem(GetItemRequest.builder()
				.tableName(tablename)
				.key(key)
				.build());
	}
	
	public GetItemResponse getAllItemsFrom (String tablename, Map<String,AttributeValue> key, List<String> attrToGet) {
		return dynamoClient.getItem(GetItemRequest.builder()
				.tableName(tablename)
				.attributesToGet(attrToGet)
				.build());
	}
	
	public GetItemResponse getAllItemsFrom (String tablename, Map<String,AttributeValue> key) {
		return dynamoClient.getItem(GetItemRequest.builder()
				.tableName(tablename)
				.build());
	}
	
	public UpdateItemResponse updateItemFrom (String tablename, Map<String,AttributeValue> key, Map<String,AttributeValueUpdate> attributeUpdates) {
		return dynamoClient.updateItem(UpdateItemRequest.builder()
				.tableName(tablename)
				.key(key)
				.attributeUpdates(attributeUpdates).build());
	}
	
}
