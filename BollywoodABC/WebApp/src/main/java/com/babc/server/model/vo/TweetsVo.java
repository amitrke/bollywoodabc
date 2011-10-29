package com.babc.server.model.vo;

import java.util.List;

import com.babc.server.model.TweetEntity;
import com.babc.server.model.TwitterUserDetailEntity;

public class TweetsVo {
	
	private TwitterUserDetailEntity twitterUserDetail;
	
	private List<TweetEntity> tweets;

	public TweetsVo(TwitterUserDetailEntity twitterUserDetail,
			List<TweetEntity> tweets) {
		super();
		this.twitterUserDetail = twitterUserDetail;
		this.tweets = tweets;
	}

	public TwitterUserDetailEntity getTwitterUserDetail() {
		return twitterUserDetail;
	}

	public List<TweetEntity> getTweets() {
		return tweets;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TweetsVo [");
		if (twitterUserDetail != null) {
			builder.append("twitterUserDetail=");
			builder.append(twitterUserDetail);
			builder.append(", ");
		}
		if (tweets != null) {
			builder.append("tweets=");
			builder.append(tweets);
		}
		builder.append("]");
		return builder.toString();
	}
}
