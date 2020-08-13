package assets;

import entities.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class WeatherParser {

    public static Weather parseWeather(String lat, String lon, Calendar from, Calendar to) throws Exception {
        Calendar today = Calendar.getInstance();
        long milliseconds = from.getTimeInMillis() - today.getTimeInMillis();
        int diff = (int)(milliseconds / (24 * 60 * 60 * 1000));//difference between today and from date

        if (diff > 14) {//смотрим погоду в это время за прошлый год
            return pastWeatherParse(lat, lon, from , to);
        }

        milliseconds = to.getTimeInMillis() - from.getTimeInMillis();
        int duration = (int) (milliseconds / (24 * 60 * 60 * 1000));

        if (diff + duration <= 7)
            return futureWeatherParse(lat, lon, diff + 1, diff + duration + 1);

       if(diff+duration>=14){//часть теперешнего прогноза join с пред годом
           from.add(Calendar.DATE,14);
           Weather mixedForecast=futureWeatherParse(lat,lon,7-((14-diff)%7),8);
           mixedForecast.join(pastWeatherParse(lat,lon,from,to));
           return mixedForecast;
       }

        if(duration>=7)
            return futureWeatherParse(lat,lon,1,8);
        else
            return futureWeatherParse(lat,lon,7-duration,8);

    }

    private static Weather futureWeatherParse (String lat,String lon,int from,int to)throws Exception{
        String urlString="https://api.darksky.net/forecast/899436ad65f0b53e40559cc502f15680/"+lat+","+lon+"?lang=en&units=si&exclude=minutely,currently,hourly}";
        String json = requestFromAPI(urlString);

        JSONObject jsonObject = new JSONObject(json);
        JSONObject daily = jsonObject.getJSONObject("daily");
        JSONArray data = (JSONArray) daily.get("data");

        Weather result=new Weather();

        for(int i=from-1;i<to;++i) {
            JSONObject container = data.getJSONObject(i);
            result.join(parseJsonContainer(container));
        }
        return result;
    }

    private static Weather pastWeatherParse(String lat,String lon,Calendar from,Calendar to) throws Exception {
        Weather result= new Weather();
        long daySec=24 * 60 * 60;
        long yearSec=daySec*365;
        long fromSec=(from.getTimeInMillis()/1000)-yearSec;
        long toSec=(to.getTimeInMillis()/1000)-yearSec;

        for(;fromSec<=toSec;fromSec+=daySec) {
            String urlString = "https://api.darksky.net/forecast/899436ad65f0b53e40559cc502f15680/" + lat + "," + lon +","+fromSec+"?lang=en&units=si&exclude=currently,flags,hourly}";
            String json = requestFromAPI(urlString);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject daily = jsonObject.getJSONObject("daily");
            JSONArray data = (JSONArray) daily.get("data");
            JSONObject container = data.getJSONObject(0);
            result.join(parseJsonContainer(container));
        }

        return result;
    }

    private static Weather parseJsonContainer(JSONObject container) {
            Weather result= new Weather();

            Double maxTemp;
            Double minTemp;
            try {
                maxTemp = (Double) container.get("apparentTemperatureMax");
            }
            catch (ClassCastException e) {
                maxTemp = (Integer) container.get("apparentTemperatureMax")/1.;
            }
            try {
                minTemp = (Double) container.get("apparentTemperatureMin");
            }
            catch (ClassCastException e) {
                minTemp = (Integer) container.get("apparentTemperatureMin")/1.;
            }
            Integer uvIndex=(Integer)container.get("uvIndex");
            Double precipProbability=0.;
            String precipType="";
            try {
                precipProbability = (Double) container.get("precipProbability");
                precipType= (String) container.get("precipType");
            }
            catch(Exception e) {
            }

        if(minTemp<=-6)
            result.tempGroupFive=true;
        if((minTemp>-6&&minTemp<4)||(result.tempGroupFive&&maxTemp>=4))
            result.tempGroupFour=true;
        if((minTemp>=4&&minTemp<13)||(minTemp<4&&maxTemp>=12))
            result.tempGroupThree=true;
        if((minTemp>=13&&minTemp<20)||(minTemp<13&&maxTemp>=19))
            result.tempGroupTwo=true;
        if(maxTemp>=20)
            result.tempGroupOne=true;
        if(precipProbability>=0.6&&precipType.equals("rain"))
            result.rainy=true;
        if(uvIndex>=4)
            result.sunny=true;
        return result;
    }

    private static String requestFromAPI(String urlString) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
