package techiearchive.aem.core.utils;

import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.i18n.LocaleUtil;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

public class TagUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagUtil.class);

    /**
     * This method is used to get a tag title
     * 
     * @param resource
     * @param tagID
     * @return
     */
    public static String getTagTitle(Resource resource, String tagID) {
        LOGGER.debug("TagUtil getTagTitle method start where resource {} and TagID {} ", resource, tagID);
        String tagTitle = StringUtils.EMPTY;
        TagManager tagManager = null;
        if (Objects.nonNull(resource)) {
            tagManager = resource.getResourceResolver().adaptTo(TagManager.class);
        }
        if (Objects.nonNull(tagManager) && StringUtils.isNotBlank(tagID)) {
            Tag tag = tagManager.resolve(tagID);
            if (Objects.nonNull(tag)) {
                String locale = SiteConfigUtil.getLocaleFromSiteRoot(resource);
                locale = StringUtils.isBlank(locale) ? SiteConfigUtil.getLocale(resource) : locale;
                tagTitle = tag.getTitle(LocaleUtil.parseLocale(locale));
            }
        }
        LOGGER.debug("TagUtil getTagTitle method end where For Tag {} Title is {} ", tagID, tagTitle);

        return tagTitle;
    }

    

    /**
     * This method is used to get a tag id
     * 
     * @param resource
     * @param tagID
     * @return
     */
    public static String getTagId(Resource resource, String tagTitle) {
        LOGGER.debug("TagUtil getTagId method start where resource {} and TagTitle {} ", resource, tagTitle);
        String tagId = StringUtils.EMPTY;
        TagManager tagManager = null;
        if (Objects.nonNull(resource)) {
            tagManager = resource.getResourceResolver().adaptTo(TagManager.class);
        }
        if (Objects.nonNull(tagManager) && StringUtils.isNotBlank(tagTitle)) {
            Tag[] tags = tagManager.findTagsByTitle(tagTitle, Locale.ENGLISH);
            if (Objects.nonNull(tags) && tags.length > 0) {
                tagId = tags[0].getTagID();
            }
        }
        LOGGER.debug("TagUtil getTagId method end where For Tag Title {} Title Id is {} ", tagTitle, tagId);

        return tagId;
    }



    /**
	 * Gets the Tag Id from JCR property
	 * 
	 * @param tag
	 * @return TagName
	 */
	public static String getTagIdValue(String tag) {
		String tagIdValue = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(tag)) {
				tagIdValue = StringUtils.substring(tag, tag.lastIndexOf("/") + 1);
			}
		return tagIdValue;
	}
    
}