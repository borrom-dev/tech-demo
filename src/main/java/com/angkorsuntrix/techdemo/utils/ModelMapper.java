package com.angkorsuntrix.techdemo.utils;

import com.angkorsuntrix.techdemo.entity.Poll;
import com.angkorsuntrix.techdemo.entity.User;
import com.angkorsuntrix.techdemo.payload.ChoiceResponse;
import com.angkorsuntrix.techdemo.payload.PollResponse;
import com.angkorsuntrix.techdemo.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static PollResponse mapPollToPollResponse(Poll poll, Map<Long, Long> choiceVotesMap, User creator, Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreateTime(poll.getCreateAt());
        pollResponse.setExpirationTime(poll.getExpirationTime());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());

            choiceResponse.setVoteCount(choiceVotesMap.getOrDefault(choice.getId(), 0L));
            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        pollResponse.setCreateBy(creatorSummary);

        if (userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices().stream().mapToLong(ChoiceResponse::getVoteCount).sum();
        pollResponse.setTotalVotes(totalVotes);

        return pollResponse;
    }
}
