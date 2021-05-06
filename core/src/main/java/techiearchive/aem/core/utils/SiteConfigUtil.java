package techiearchive.aem.core.utils;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import techiearchive.aem.core.models.SiteConfigModel;

public class SiteConfigUtil {
	
	public static SiteConfigModel getSiteSpecificConfigModel(Resource resource, String configName) {
        SiteConfigModel siteConfigModel = null;
        if (Objects.nonNull(resource)) {
            resource = resource.getResourceResolver().getResource(getConfigPath(resource, configName));
            if (Objects.nonNull(resource)) {
                siteConfigModel = resource.adaptTo(SiteConfigModel.class);
            }
        }
        return siteConfigModel;
    }

	public static String getConfigPath(Resource resource, String configName) {
		return getRootPagePath(resource) + "/site-config-page/jcr:content/root" + "/" + configName;
	}
	
	public static String getRootPagePath(Resource resource) {
        Page currentPage;
        String path = StringUtils.EMPTY;
        String langName = null;
        if (Objects.nonNull(resource)) {
            PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);
			if (Objects.nonNull(pageManager)) {
				currentPage = pageManager.getContainingPage(resource.getPath());
				final String[] nodes = currentPage.getPath().split("/");
				langName = nodes[3];
				if (StringUtils.isNotBlank(langName)) {
					path = "/content/techiearchive-aem/" + langName;
				}
			}
        }
        return path;
    }

}
