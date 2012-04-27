package com.babc.server.model.vo;

import java.util.Date;

import com.babc.server.utils.AppUtils;

public class TweetListVo {
	private String name;
	private Long facePic;
	private Long tagId;
	private String tweet;
	private String tweetDesc;
	private Date createdAt;

	/**
	 * @param name
	 * @param facePic
	 * @param tagId
	 * @param tweet
	 * @param tweetDesc
	 */
	public TweetListVo(String name, Long facePic, Long tagId, String tweet,
			String tweetDesc, Date createdAt) {
		super();
		this.name = name;
		this.facePic = facePic;
		this.tagId = tagId;
		this.tweet = tweet;
		this.tweetDesc = tweetDesc;
		this.createdAt = createdAt;
	}
	
	public String getFormattedCreatedAt(){
		return AppUtils.formatDate(createdAt);
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public String getTitleUrl(){
		return AppUtils.urlFormat(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TweetListVo() {
		super();
	}

	public Long getFacePic() {
		return facePic;
	}

	public void setFacePic(Long facePic) {
		this.facePic = facePic;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getTweetDesc() {
		return tweetDesc;
	}

	public void setTweetDesc(String tweetDesc) {
		this.tweetDesc = tweetDesc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TweetListVo [");
		if (facePic != null) {
			builder.append("facePic=");
			builder.append(facePic);
			builder.append(", ");
		}
		if (tagId != null) {
			builder.append("tagId=");
			builder.append(tagId);
			builder.append(", ");
		}
		if (tweet != null) {
			builder.append("tweet=");
			builder.append(tweet);
			builder.append(", ");
		}
		if (tweetDesc != null) {
			builder.append("tweetDesc=");
			builder.append(tweetDesc);
		}
		builder.append("]");
		return builder.toString();
	}
}
