package run.halo.app.tools;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Title: IpInfoDTO
 * </p>
 * <p>
 * Description: IP Information data transmission object
 * </p>
 *
 * @author ssatwa
 */
@Data
@ApiModel(value = "IP information data transmission object", description = "IP information data transmission object")
public class IpInfoDTO {
	@ApiModelProperty(value = "IP address")
	String ipAddress;
	@ApiModelProperty(value = "Country Abbreviation")
	String countryShort;
	@ApiModelProperty(value = "Country Name")
	String countryLong;
	@ApiModelProperty(value = "Province")
	String region;
	@ApiModelProperty(value = "City")
	String city;
	@ApiModelProperty(value = "operator")
	String isp;
	@ApiModelProperty(value = "Latitude")
	Float latitude;
	@ApiModelProperty(value = "longitude")
	Float longitude;
	@ApiModelProperty(value = "Domain")
	String domain;
	@ApiModelProperty(value = "Zip Code")
	String zipcode;
	@ApiModelProperty(value = "Network Type")
	String netspeed;
	@ApiModelProperty(value = "Time Zone")
	String timezone;
	@ApiModelProperty(value = "International Area Code")
	String iddcode;
	@ApiModelProperty(value = "International Area Code")
	String areacode;
	String weatherstationcode;
	String weatherstationname;
	String mcc;
	String mnc;
	String mobilebrand;
	Float elevation;
	String usagetype;
	String status;
	Boolean delay = false;
}