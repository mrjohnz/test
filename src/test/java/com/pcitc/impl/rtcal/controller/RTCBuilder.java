package com.pcitc.impl.rtcal.controller;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.pcitc.common.collectionjson.model.DataEntry;
import com.pcitc.common.collectionjson.model.Item;
import com.pcitc.common.collectionjson.model.Template;
import com.pcitc.common.collectionjson.model.builder.CollectionBuilder;
import com.pcitc.common.collectionjson.model.data.StringDataEntry;
import com.pcitc.common.collectionjson.serialization.Serializer;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class RTCBuilder extends BaseResRep implements Serializable{
	
	public static String getAddAppParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/realTimeCalcService/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/realTimeCalcService/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");
		StringDataEntry createUser = new StringDataEntry("createUser", "");

		item.addData(name);
		item.addData(code);
		item.addData(createUser);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "66116");
		StringDataEntry name1 = new StringDataEntry("name", "  appname");
		StringDataEntry createUser1 = new StringDataEntry("createUser", "ghyh");
		//StringDataEntry updateUser1 = new StringDataEntry("updateUser", "ghyh");

		data.add(name1);
		data.add(code1);
		data.add(createUser1);
		//data.add(updateUser1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	
	public static String updateAppParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
//		StringDataEntry code1 = new StringDataEntry("code", "567");
		StringDataEntry name1 = new StringDataEntry("name", "  appnaji  ");
		StringDataEntry updateUser = new StringDataEntry("updateUser", " appnaji  ");

		data.add(name1);
		data.add(updateUser);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng1() throws URISyntaxException {

		String baseHref = "/realTimeCalcService/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/realTimeCalcService/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");
		StringDataEntry createUser = new StringDataEntry("createUser", "");

		item.addData(name);
		item.addData(code);
		item.addData(createUser);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("appCode", "66216");
		StringDataEntry name1 = new StringDataEntry("name", "  appname");
		StringDataEntry nodeId = new StringDataEntry("nodeId", "10009");
		StringDataEntry createUser1 = new StringDataEntry("crtUserName", "ghyh");
		StringDataEntry updateUser1 = new StringDataEntry("updateUser", "ghyh");
		StringDataEntry formula = new StringDataEntry("formula", "RTDB('104_FIQ_0903')*0.85");
		StringDataEntry calcFrequency = new StringDataEntry("calcFrequency", "120");
		StringDataEntry deviation = new StringDataEntry("deviation", "120");
		StringDataEntry nextTime = new StringDataEntry("nextTime", "2016-12-29 15:50:33");
		data.add(name1);
		data.add(code1);
		data.add(nodeId);
		data.add(createUser1);
		data.add(updateUser1);
		data.add(formula);
		data.add(calcFrequency);
		data.add(deviation);
		data.add(nextTime);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	
	public static String getAddStudentParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/a/students";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/a/students"), null);
		StringDataEntry id = new StringDataEntry("id", "");
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry age = new StringDataEntry("age", "");

		item.addData(id);
		item.addData(name);
		item.addData(age);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry id1 = new StringDataEntry("id", "5");
		StringDataEntry name1 = new StringDataEntry("name", "aaaa");
		StringDataEntry age1 = new StringDataEntry("age", "26");
		//StringDataEntry updateUser1 = new StringDataEntry("updateUser", "ghyh");
		
		data.add(id1);
		data.add(name1);
		data.add(age1);
		//data.add(updateUser1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	
	public static String updateStudentParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/a/students";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/a/students"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry age = new StringDataEntry("age", "");

		item.addData(name);
		item.addData(age);
		
		List<DataEntry> data = new ArrayList();
//		StringDataEntry code1 = new StringDataEntry("code", "567");
		StringDataEntry name1 = new StringDataEntry("name", "  xiaoer  ");
		StringDataEntry age1 = new StringDataEntry("age", " 20  ");

		data.add(name1);
		data.add(age1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	
	public static String getAddStudentParamCollectionStirng1() throws URISyntaxException {

		String baseHref = "/a/students";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/a/students"), null);
		StringDataEntry id = new StringDataEntry("id", "");
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry age = new StringDataEntry("age", "");

		item.addData(id);
		item.addData(name);
		item.addData(age);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry id1 = new StringDataEntry("id", "66216");
		StringDataEntry name1 = new StringDataEntry("name", "  appname");
		StringDataEntry age1 = new StringDataEntry("age", "10009");
		
		data.add(id1);
		data.add(name1);
		data.add(age1);
		
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
}
