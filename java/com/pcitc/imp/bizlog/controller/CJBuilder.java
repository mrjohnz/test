package com.pcitc.imp.bizlog.controller;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.expression.spel.ast.StringLiteral;

import com.pcitc.imp.bizlog.model.App;

import de.fesere.hypermedia.cj.model.DataEntry;
import de.fesere.hypermedia.cj.model.Item;
import de.fesere.hypermedia.cj.model.Link;
import de.fesere.hypermedia.cj.model.Query;
import de.fesere.hypermedia.cj.model.Template;
import de.fesere.hypermedia.cj.model.builder.CollectionBuilder;
import de.fesere.hypermedia.cj.model.builder.LinkBuilder;
import de.fesere.hypermedia.cj.model.data.StringDataEntry;
import de.fesere.hypermedia.cj.serialization.Serializer;
import pcitc.imp.common.cjtool.baseresrep.BaseResRep;

public class CJBuilder extends BaseResRep implements Serializable{
	
	public static String getStirngCollection() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		String baseHref1 = "/bizlog/apps/666/logs";
		URI href1 = new URI(baseHref1);
		
		List<Link> links = new ArrayList<>();
		
		Link link = new Link("", href1, "");
		
		links.add(link);
		
		List<Item> items = new ArrayList<>();

		Item item = new Item(new URI("/bizlog/apps/666"), links);

		StringDataEntry code = new StringDataEntry("code", "666");
		StringDataEntry name = new StringDataEntry("name", "appname");
		item.addData(code);
		item.addData(name);
		items.add(item);
		
		List<DataEntry> data = new ArrayList();

		StringDataEntry code1 = new StringDataEntry("code", "");
		StringDataEntry name1 = new StringDataEntry("name", "");

		data.add(code1);
		data.add(name1);
//---------------------------------------------------------------------------
		Template template = new Template(data);

		StringDataEntry name2 = new StringDataEntry("$codeList", "");
		StringDataEntry name3 = new StringDataEntry("$skip", "");
		StringDataEntry name4 = new StringDataEntry("$top", "");
		
		List<DataEntry> data1 = new ArrayList();
		data1.add(name2);
		data1.add(name3);
		data1.add(name4);
		Query qu = new Query(href, "search", "查询", data1);
//---------------------------------------------------------------------------
		CollectionBuilder collection = new CollectionBuilder(href);
		
		collection.addItem(item);
		collection.addQuery(qu);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getStirngCollection1() throws URISyntaxException {

		String baseHref = "/bizlog/apps/tst1";
		URI href = new URI(baseHref);
		
		String baseHref1 = "/bizlog/apps/tst1/logs";
		URI href1 = new URI(baseHref1);
		
		List<Link> links = new ArrayList<>();
		
		Link link = new Link("", href1, "");
		
		links.add(link);
		
		List<Item> items = new ArrayList<>();

		Item item = new Item(new URI("/bizlog/apps/tst1"), links);

		StringDataEntry code = new StringDataEntry("code", "tst1");
		StringDataEntry name = new StringDataEntry("name", "asd");
		item.addData(code);
		item.addData(name);
		items.add(item);
		
		List<DataEntry> data = new ArrayList();

		StringDataEntry code1 = new StringDataEntry("code", "");
		StringDataEntry name1 = new StringDataEntry("name", "");

		data.add(code1);
		data.add(name1);
//---------------------------------------------------------------------------
		Template template = new Template(data);

		StringDataEntry name2 = new StringDataEntry("$codeList", "");
		StringDataEntry name3 = new StringDataEntry("$skip", "");
		StringDataEntry name4 = new StringDataEntry("$top", "");
		
		List<DataEntry> data1 = new ArrayList();
		data1.add(name2);
		data1.add(name3);
		data1.add(name4);
		Query qu = new Query(href, "search", "查询", data1);
//---------------------------------------------------------------------------
		CollectionBuilder collection = new CollectionBuilder(href);
		
		collection.addItem(item);
		collection.addQuery(qu);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddApp() throws URISyntaxException {
		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "5678");
		StringDataEntry name1 = new StringDataEntry("name", "name56");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogs() throws URISyntaxException {
		String baseHref = "/bizlog";
		URI href = new URI(baseHref);
		
		List<DataEntry> data = new ArrayList();
		
		StringDataEntry clientip = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry topic = new StringDataEntry("topic", "add");
		StringDataEntry usercode = new StringDataEntry("usercode", "whw");
		StringDataEntry username = new StringDataEntry("username", "王");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "20160929164400");
		StringDataEntry operation = new StringDataEntry("operation", "add");
		StringDataEntry operobj = new StringDataEntry("operobj", "order");
		StringDataEntry description = new StringDataEntry("description", "123");
		StringDataEntry result = new StringDataEntry("result", "sucess");
		StringDataEntry exception = new StringDataEntry("exception", "");

		data.add(clientip);
		data.add(topic);
		data.add(usercode);
		data.add(username);
		data.add(timestamp);
		data.add(operation);
		data.add(operobj);
		data.add(description);
		data.add(result);
		data.add(exception);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "6616");
		StringDataEntry name1 = new StringDataEntry("name", "  appname");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng123() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "  667 ");
		StringDataEntry name1 = new StringDataEntry("name", "  name");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng456() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "   ");
		StringDataEntry name1 = new StringDataEntry("name", "  dfdaf");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng789() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "  3445 ");
		StringDataEntry name1 = new StringDataEntry("name", "  ");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddAppParamCollectionStirng901() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry code1 = new StringDataEntry("code", "  34#￥%……45 ");
		StringDataEntry name1 = new StringDataEntry("name", " 4545 ");

		data.add(name1);
		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/perfcode78/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we1");
		StringDataEntry operation1 = new StringDataEntry("operation", "er1");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt1");
		StringDataEntry exception1 = new StringDataEntry("exception", "13");
		StringDataEntry result1 = new StringDataEntry("result", "yu1");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "123");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "456133");
		StringDataEntry username1 = new StringDataEntry("username", "pp1");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirngq() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/666/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description = new StringDataEntry("description", "we1");
		StringDataEntry operation = new StringDataEntry("operation", "er1");
		StringDataEntry operobj = new StringDataEntry("operobj", "rt1");
		StringDataEntry exception = new StringDataEntry("exception", "11");
		StringDataEntry result = new StringDataEntry("result", "yu1");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic = new StringDataEntry("topic", "★☆我是中国人1");
		StringDataEntry usercode = new StringDataEntry("usercode", "456133");
		StringDataEntry username = new StringDataEntry("username", "pp1");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "");
		StringDataEntry description1 = new StringDataEntry("description", "");
		StringDataEntry operation1 = new StringDataEntry("operation", "");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "");
		StringDataEntry exception1 = new StringDataEntry("exception", "");
		StringDataEntry result1 = new StringDataEntry("result", "");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "");
		StringDataEntry topic1 = new StringDataEntry("topic", "");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "");
		StringDataEntry username1 = new StringDataEntry("username", "");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng1() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "er");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "456");
		StringDataEntry username1 = new StringDataEntry("username", "pp");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng2() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "er");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "");
		StringDataEntry username1 = new StringDataEntry("username", "pp");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng3() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "er");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "we");
		StringDataEntry username1 = new StringDataEntry("username", "");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng4() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "we");
		StringDataEntry username1 = new StringDataEntry("username", "ww");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng5() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "33");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "we");
		StringDataEntry username1 = new StringDataEntry("username", "ww");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng6() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "33");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "asd");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "we");
		StringDataEntry username1 = new StringDataEntry("username", "ww");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
		Template template = new Template(data);
		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);
		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogParamCollectionStirng7() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps/5678/logs"), null);
		
		StringDataEntry clientip = new StringDataEntry("clientip", "");
		StringDataEntry description = new StringDataEntry("description", "");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "");
		StringDataEntry exception = new StringDataEntry("exception", "");
		StringDataEntry result = new StringDataEntry("result", "");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "");
		StringDataEntry topic = new StringDataEntry("topic", "");
		StringDataEntry usercode = new StringDataEntry("usercode", "");
		StringDataEntry username = new StringDataEntry("username", "");
		
		item.addData(clientip);
		item.addData(description);
		item.addData(operation);
		item.addData(operobj);
		item.addData(exception);
		item.addData(result);
		item.addData(timestamp);
		item.addData(topic);
		item.addData(usercode);
		item.addData(username);
		
		List<DataEntry> data = new ArrayList();
		StringDataEntry clientip1 = new StringDataEntry("clientip", "10.246.6.130.111");
		StringDataEntry description1 = new StringDataEntry("description", "we");
		StringDataEntry operation1 = new StringDataEntry("operation", "33");
		StringDataEntry operobj1 = new StringDataEntry("operobj", "rt");
		StringDataEntry exception1 = new StringDataEntry("exception", "1");
		StringDataEntry result1 = new StringDataEntry("result", "yu");
		StringDataEntry timestamp1 = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry topic1 = new StringDataEntry("topic", "io");
		StringDataEntry usercode1 = new StringDataEntry("usercode", "we");
		StringDataEntry username1 = new StringDataEntry("username", "ww");
		data.add(clientip1);
		data.add(description1);
		data.add(operation1);
		data.add(operobj1);
		data.add(exception1);
		data.add(result1);
		data.add(timestamp1);
		data.add(topic1);
		data.add(usercode1);
		data.add(username1);
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

		data.add(name1);
//		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String updateAppParamCollectionStirng123() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
//		StringDataEntry code1 = new StringDataEntry("code", "567");
		StringDataEntry name1 = new StringDataEntry("name", " ");

		data.add(name1);
//		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String updateAppParamCollectionStirng456() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
//		StringDataEntry code1 = new StringDataEntry("code", "567");
		StringDataEntry name1 = new StringDataEntry("name", " @#$&*~~~~~");

		data.add(name1);
//		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String updateAppParamCollectionStirng789() throws URISyntaxException {

		String baseHref = "/bizlog/apps";
		URI href = new URI(baseHref);
		
		Item item = new Item(new URI("/bizlog/apps"), null);
		StringDataEntry name = new StringDataEntry("name", "");
		StringDataEntry code = new StringDataEntry("code", "");

		item.addData(name);
		item.addData(code);
		
		List<DataEntry> data = new ArrayList();
//		StringDataEntry code1 = new StringDataEntry("code", "567");
		StringDataEntry name1 = new StringDataEntry("name", " 345");

		data.add(name1);
//		data.add(code1);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addItem(item);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
	
	public static String getAddLogsWithOpisNull() throws URISyntaxException {
		String baseHref = "/bizlog";
		URI href = new URI(baseHref);
		
		List<DataEntry> data = new ArrayList();
		
		StringDataEntry clientip = new StringDataEntry("clientip", "10.246.6.130");
		StringDataEntry topic = new StringDataEntry("topic", "add");
		StringDataEntry usercode = new StringDataEntry("usercode", "whw");
		StringDataEntry username = new StringDataEntry("username", "w");
		StringDataEntry timestamp = new StringDataEntry("timestamp", "201609291644000");
		StringDataEntry operation = new StringDataEntry("operation", "");
		StringDataEntry operobj = new StringDataEntry("operobj", "order");
		StringDataEntry description = new StringDataEntry("description", "123");
		StringDataEntry result = new StringDataEntry("result", "sucess");
		StringDataEntry exception = new StringDataEntry("exception", "");

		data.add(clientip);
		data.add(topic);
		data.add(usercode);
		data.add(username);
		data.add(timestamp);
		data.add(operation);
		data.add(operobj);
		data.add(description);
		data.add(result);
		data.add(exception);
		Template template = new Template(data);

		CollectionBuilder collection = new CollectionBuilder(href);
		collection.addTemplate(template);

		return new Serializer().serialize(collection.build());
	}
}
