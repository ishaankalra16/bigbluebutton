/**
* BigBlueButton open source conferencing system - http://www.bigbluebutton.org/
* 
* Copyright (c) 2012 BigBlueButton Inc. and by respective authors (see below).
*
* This program is free software; you can redistribute it and/or modify it under the
* terms of the GNU Lesser General Public License as published by the Free Software
* Foundation; either version 3.0 of the License, or (at your option) any later
* version.
* 
* BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
* PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License along
* with BigBlueButton; if not, see <http://www.gnu.org/licenses/>.
*
*/

package org.bigbluebutton.conference.service.poll;

import java.util.HashMap;
import java.util.List;

public class PollVO{
	private String pollID;
	private String meetingID;
	private String title;
	private String question;
	private String datetime;
	private List<AnswerVO> answers;

	public Poll(String meetingID, String title, String question, List<AnswerVO> answers) {
		this.meetingID = meetingID;
		this.title = title;
		this.question = question;
		this.datetime = Long.toString(System.currentTimeMillis()); 
		this.answers = AnswerVO.toAnswerVOList(meetingID, null, answers);
	}

	public void addAnswer(String answer){
		String answerID = generateAnswerID(this.meetingID);
		this.answers.put(answerID,answer);
	}

	public void removeAnswer(String answerID){
		this.answers.remove(answerID);
	}

	public String generatePollID(String meetingID){
		return null;
	}

	public String generateAnswerID(String meetingID){
		return null;
	}

	public void store() throws Exception{

	}

	public String getMeetingID(){
		return this.meetingID;
	}

	public String getPollID(){
		return this.pollID;
	}

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("pollID",pollID);
		map.put("meetingID",meetingID);
		map.put("title", title);
		map.put("question",question);
		map.put("datetime",datetime);
		return map;
	}

	class AnswerVO{
		String answerId;
		String pollId;
		String meetingId;
		String answer;

		public AnswerVO(String meetingId, String pollId, String answer){
			this.pollId = pollId;
			this.answer = answer;
		}

		public static List<AnswerVO> toAnswerVOList(String meetingId, String pollId, List<AnswerVO> answers){
			List<AnswerVO> list = ArrayList<AnswerVO>();
			for(String ans:answers){
				AnswerVO ansvo = new AnswerVO(meetingId,pollId,ans);
				list.add(ansvo);
			}
			return list;
		}
	}

}