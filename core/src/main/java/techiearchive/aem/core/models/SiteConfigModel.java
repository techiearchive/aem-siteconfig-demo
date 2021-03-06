package techiearchive.aem.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import techiearchive.aem.core.utils.TagUtil;

/**
 * This Model class is used to get site specific configuration
 * 
 */
@Model(adaptables = Resource.class, resourceType = "techiearchive-aem/components/content/site-config", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = {
		@ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class SiteConfigModel {

	private static final Logger LOG = LoggerFactory.getLogger(SiteConfigModel.class);

	@ValueMapValue
	String logoIcon;
	
	@ValueMapValue
	String logoIconAltText;

	@ValueMapValue
	String logoLinkURL;
	
	@ValueMapValue
	String region;

	@Self
	private Resource resource;
	
	@Getter
	String regionCode;
	
	@Getter
	String regionName;

	@PostConstruct
	protected void init() {
		LOG.debug("site config initialized");
		
		regionName = TagUtil.getTagTitle(resource, region);
		regionCode = TagUtil.getTagIdValue(region);
	}

	public String getLogoIcon() {
		return logoIcon;
	}

	public String getLogoIconAltText() {
		return logoIconAltText;
	}

	public String getLogoLinkURL() {
		return logoLinkURL;
	}

}