package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.dto.OpinionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpinionMapper implements Mapper<Opinion, OpinionDto> {

    @Override
    public OpinionDto convert(Opinion from) {
        return OpinionDto.builder()
                .score(from.getScore())
                .text(from.getText())
                .build();
    }

    @Override
    public Opinion reverse(OpinionDto from) {
        return Opinion.builder()
                .score(from.getScore())
                .text(from.getText())
                .build();
    }

    public Opinion reverseSave(OpinionDto from, User user) {
        return Opinion.builder()
                .score(from.getScore())
                .text(from.getText())
                .user(user)
                .build();
    }

    public List<OpinionDto> convertList(List<Opinion> opinions) {
        List<OpinionDto> opinionsRequest = new ArrayList<>();
        for (Opinion opinion : opinions) {
            opinionsRequest.add(convert(opinion));
        }
        return opinionsRequest;
    }
}
