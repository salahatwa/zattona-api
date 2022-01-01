package run.halo.app.tools.services;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import run.halo.app.config.properties.HaloProperties;
import run.halo.app.exception.ServiceException;
import run.halo.app.generator.utils.BeanUtils;
import run.halo.app.tools.IpInfoDTO;
import run.halo.app.tools.ip2location.IP2Location;
import run.halo.app.tools.ip2location.IPResult;
import run.halo.app.utils.Builder;
import run.halo.app.utils.HaloUtils;

/**
 * <p>
 * Title: IpService
 * </p>
 * <p>
 * Description: I
 * </p>
 *
 * @author ssatwa
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "IpService")
public class IpService {
	private static final String OK = "OK";
	private static final String EMPTY_IP_ADDRESS = "EMPTY_IP_ADDRESS";
	private static final String INVALID_IP_ADDRESS = "INVALID_IP_ADDRESS";
	private static final String MISSING_FILE = "MISSING_FILE";
	private static final String IPV6_NOT_SUPPORTED = "IPV6_NOT_SUPPORTED";
	private static final Long IPV4_BIG_INTEGER = 4294967295L;
	private IP2Location ip2LocationV4;
	private IP2Location ip2LocationV6;


	public IpService(HaloProperties haloProperties) {
		if (!BeanUtils.isEmpty(haloProperties.getIpv4DataPath())) {
			this.ip2LocationV4 = new IP2Location();
			this.ip2LocationV4.IPDatabasePath = haloProperties.getIpv4DataPath();
		}
		if (!BeanUtils.isEmpty(haloProperties.getIpv6DataPath())) {
			this.ip2LocationV6 = new IP2Location();
			this.ip2LocationV6.IPDatabasePath = haloProperties.getIpv6DataPath();
		}
	}

	@Cacheable
	public IpInfoDTO query(String ip) throws ServiceException {
		if (BeanUtils.isEmpty(ip)) {
			throw new ServiceException("IP address cannot be null.");
		}
		IPResult rec;
		try {
			BigInteger bIp = HaloUtils.stringToBigInt(ip);
			if (BigInteger.valueOf(IPV4_BIG_INTEGER).compareTo(bIp) > 0) {
				// IPv4
				if (this.ip2LocationV4 == null) {
					throw new ServiceException("IPv4 address query is not supported (IPv4 is not supported.)");
				}
				rec = this.ip2LocationV4.IPQuery(ip);
			} else if (this.ip2LocationV6 != null) {
				// IPv6
				rec = this.ip2LocationV6.IPQuery(ip);
			} else {
				throw new ServiceException("IPv6 query is not supported (IPv6 is not supported.)");
			}
			if (OK.equals(rec.getStatus())) {
				return convert(rec);
			} else if (EMPTY_IP_ADDRESS.equals(rec.getStatus())) {
				throw new ServiceException("IP address cannot be blank.");
			} else if (INVALID_IP_ADDRESS.equals(rec.getStatus())) {
				throw new ServiceException("Invalid IP address.");
			} else if (MISSING_FILE.equals(rec.getStatus())) {
				throw new ServiceException("Invalid database path.");
			} else if (IPV6_NOT_SUPPORTED.equals(rec.getStatus())) {
				throw new ServiceException("Does not contain IPv6 data (This BIN does not contain IPv6 data.)");
			} else {
				throw new ServiceException("Unknown error::" + rec.getStatus());
			}
		} catch (NumberFormatException numberFormatException) {
			throw new ServiceException("Invalid IP address.");
		} catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
			throw new ServiceException("Invalid IP address.");
		} catch (IOException ioException) {
			log.error(ioException.getMessage(), ioException);
			throw new ServiceException("IOException.");
		}
	}

	private IpInfoDTO convert(IPResult ipResult) {
		return Builder.of(IpInfoDTO::new).with(IpInfoDTO::setIpAddress, ipResult.getIp_address())
				.with(IpInfoDTO::setCountryShort, ipResult.getCountry_short())
				.with(IpInfoDTO::setCountryLong, ipResult.getCountry_long())
				.with(IpInfoDTO::setRegion, ipResult.getRegion()).with(IpInfoDTO::setCity, ipResult.getCity())
				.with(IpInfoDTO::setIsp, ipResult.getISP()).with(IpInfoDTO::setLatitude, ipResult.getLatitude())
				.with(IpInfoDTO::setLongitude, ipResult.getLongitude()).with(IpInfoDTO::setDomain, ipResult.getDomain())
				.with(IpInfoDTO::setZipcode, ipResult.getZipCode()).with(IpInfoDTO::setNetspeed, ipResult.getNetSpeed())
				.with(IpInfoDTO::setTimezone, ipResult.getTimeZone()).with(IpInfoDTO::setIddcode, ipResult.getIDDCode())
				.with(IpInfoDTO::setAreacode, ipResult.getAreaCode())
				.with(IpInfoDTO::setWeatherstationcode, ipResult.getWeatherStationCode())
				.with(IpInfoDTO::setWeatherstationname, ipResult.getWeatherStationName())
				.with(IpInfoDTO::setMcc, ipResult.getMCC()).with(IpInfoDTO::setMnc, ipResult.getMNC())
				.with(IpInfoDTO::setMobilebrand, ipResult.getMobileBrand())
				.with(IpInfoDTO::setElevation, ipResult.getElevation())
				.with(IpInfoDTO::setUsagetype, ipResult.getUsageType()).with(IpInfoDTO::setStatus, ipResult.getStatus())
				.build();
	}
}
