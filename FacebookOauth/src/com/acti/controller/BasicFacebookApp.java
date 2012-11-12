package com.acti.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adaptavant.dto.UserDetails;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@Controller
public class BasicFacebookApp {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homeController(){
		return "welcome";
	}
	
	@RequestMapping(value="/sendfb",method=RequestMethod.GET)
	public void rediredttoFacebook(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session=request.getSession(true);
		String redirest_Facebool="https://www.facebook.com/dialog/oauth/?" +
				"client_id=XXXXXXXXXX&" +
				"redirect_uri=http://xxxxxxxxxxxxxxxxxxx.com/gettingCode&" +
				"response_type=code&" +
				"state=litu101";
		response.sendRedirect(redirest_Facebool);
	}
	
	@RequestMapping(value="/gettingCode",method=RequestMethod.GET)
	public String postProcessingfb(HttpServletRequest request){
		HttpSession session=request.getSession(false);
		if(session!=null){
		UserDetails user=new UserDetails();
		String fb_code=request.getParameter("code");
		String accessToken="";
		System.out.println("code::"+fb_code);
		//http://facebookoauthforbasicinformati.appspot.com
		//http://localhost:8888/gettingCode
		try {
        	String inputLine;
        	String url_For_AccesToken= "https://graph.facebook.com/oauth/access_token?client_id=xxxxxxxxxx&" +
            		"redirect_uri=http://xxxxxxxxxxxxxxxx.com/gettingCode&" +
            		"client_secret=xxxxxxxxxxxxxxxxxx&" +
            		"code="+fb_code;
            URL url=new URL(url_For_AccesToken);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            BufferedReader bufferReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer=new StringBuffer();
            while ((inputLine=bufferReader.readLine())!=null)
            		stringBuffer=stringBuffer.append(inputLine+"\n");
            bufferReader.close();
            accessToken=stringBuffer.toString();
        }catch (Exception token_Exception) {
			// TODO: handle exception
        	token_Exception.printStackTrace();
        	System.out.println("problem in getting acces token");
		}
        System.out.println("acces token::"+accessToken);
        String graph = "";
        try{
        	String graph_Inputline;
        	String graph_requestURL="https://graph.facebook.com/me?fields=id,name,link,gender,first_name,last_name,username,locale&"+accessToken;
        	URL graph_URL=new URL(graph_requestURL);
        	URLConnection graph_urlConnection=graph_URL.openConnection();
        	BufferedReader graph_bufferReader=new BufferedReader(new InputStreamReader(graph_urlConnection.getInputStream()));
            //StringBuffer graph_stringBuffer=new StringBuffer();
        	String graph_stringBuffer = "";
            while ((graph_Inputline = graph_bufferReader.readLine()) != null)
                //graph_stringBuffer.append(graph_Inputline + "\n");
            	graph+=graph_Inputline;
            graph_bufferReader.close();
            //graph=graph_bufferReader.toString();
        }catch (Exception graph_Exception) {
			// TODO: handle exception
        	//graph_Exception.printStackTrace();
        	System.out.println("problem in accessing grap api");
		}
        System.out.println("json::"+graph);
        try{
        	JSONObject json=new JSONObject(graph);
        	user.setId(json.getString("id"));
        	
        	user.setName(json.getString("name"));
        	
        	user.setLink(json.getString("link"));
        	
        	user.setGender(json.getString("gender"));
        	
        	user.setFirst_name(json.getString("first_name"));
        	
        	user.setLast_name(json.getString("last_name"));
        	
        	user.setUsername(json.getString("username"));
        	
        	user.setLocale(json.getString("locale"));
        	
        	session.setAttribute("user",user);
        	return "userdetails";
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	}
		return null;
	}
}
