package application.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * This class initialises a request to an external geolocation web api service
 * @author Uzoma Oseji - 1715756
 *
 */
public class ServerWebApi {

    private String status = "", isStatusActive = "";
    private String country = "", isCountryActive = "";
    private String countryCode = "", isCountryCodeActive = "";
    private String region = "", isRegionActive = "";
    private String regionName = "", isRegionNameActive = "";
    private String city = "", isCityActive = "";
    private String zip = "", isZipActive = "";
    private String timezone = "", isTimezoneActive = "";
    private String org = "", isOrgActive = "";
    private String ip = "", isIpActive = "";
    
    
    /**
     * This method is an example of a simple request to get JSON data from a web service
     */
	public void startWebApi() 
	{
		String wordSplit = "";
		
        URL url;

        
        try {
        	
        	// requesting a JSON data from web api service -> ip-api.com
        	
            url = new URL("http://ip-api.com/json/?fields=status,message,country,countryCode,region,regionName,city,zip,timezone,org,query");

            InputStream inputStream = url.openStream();

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }

            //System.out.println(result.toString("UTF-8"));
            wordSplit = result.toString("UTF-8");
                        
        } catch (Exception e) {
            System.out.println("An Error occured: "+e.toString());
            e.printStackTrace();
        }
        
        
        // Sorting the JSON return data and initialising them to their appropriate fields
       String[] words = wordSplit.split("\\W+");
        
        for (int i = 0; i < words.length; i++) 
        {
        	if (words[i].equals("status"))
        	{isStatusActive = "1"; continue;}
        	
        	if (words[i].equals("country")) 
        	{isStatusActive = "0"; isCountryActive = "1";continue;}
        	
        	if (words[i].equals("countryCode")) 
        	{isCountryActive = "0"; isCountryCodeActive = "1"; continue;}
        	
        	if (words[i].equals("region")) 
        	{isCountryCodeActive = "0"; isRegionActive = "1";continue;}
        	
        	if (words[i].equals("regionName")) 
        	{isRegionActive = "0"; isRegionNameActive = "1";continue;}
        	
        	if (words[i].equals("city"))
        	{isRegionNameActive = "0"; isCityActive = "1";continue;}
        	
        	if (words[i].equals("zip"))
        	{isCityActive = "0"; isZipActive = "1";continue;}
        	
        	if (words[i].equals("timezone"))
        	{isZipActive = "0"; isTimezoneActive = "1";continue;}
        	
        	if (words[i].equals("org"))
        	{isTimezoneActive = "0"; isOrgActive = "1";continue;}
        	
        	if (words[i].equals("query")) 
        	{isOrgActive = "0"; isIpActive = "1";continue;}
        	
        	
        	if (isStatusActive.equals("1")) 
        	{status = status + words[i] + " ";}
        	
        	if (isCountryActive.equals("1")) 
        	{country = country + words[i] + " ";}
        	
        	if (isCountryCodeActive.equals("1")) 
        	{countryCode = countryCode + words[i] + " ";}
        	
        	if (isRegionActive.equals("1")) 
        	{region = region + words[i] + " ";}
        	
        	if (isRegionNameActive.equals("1")) 
        	{regionName = regionName + words[i] + " ";}
        	
        	if (isCityActive.equals("1")) 
        	{city = city + words[i] + " ";}
        	
        	if (isZipActive.equals("1")) 
        	{zip = zip + words[i] + " ";}
        	
        	if (isTimezoneActive.equals("1")) 
        	{timezone = timezone + words[i] + " ";}
        	
        	if (isOrgActive.equals("1")) 
        	{org = org + words[i] + " ";}
        	
        	if (isIpActive.equals("1")) 
        	{ip = ip + words[i] + ".";}
        	
        }
        
        // cleaning return data
        status = status.trim();
        country = country.trim();
        countryCode = countryCode.trim();
        region = region.trim();
        regionName = regionName.trim();
        city = city.trim();
        zip = zip.trim();
        timezone = timezone.trim();
        org = org.trim();
        ip = ip.substring(0, ip.length() - 1);
    }
	
	
	/**
	 * Returns IP Address
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Returns Status
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Returns Country
	 * @return
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Returns Country Code
	 * @return
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/**
	 * Returns Region
	 * @return
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * Returns Region Name
	 * @return
	 */
	public String getRegionName() {
		return regionName;
	}
	
	/**
	 * Returns City
	 * @return
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Returns Zip Code
	 * @return
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * Returns Time Zone
	 * @return
	 */
	public String getTimezone() {
		return timezone;
	}
	
	/**
	 * Returns Organisation
	 * @return
	 */
	public String getOrg() {
		return org;
	}
	
}
