package com.example.demo.shop.mapping;

import com.example.demo.shop.dto.OpinionDto;
import com.example.demo.shop.model.Opinion;
import com.example.demo.shop.model.User;
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
        List<OpinionDto> opinionsDto = new ArrayList<>();
        for (Opinion opinion : opinions) {
            opinionsDto.add(convert(opinion));
        }
        return opinionsDto;
    }
}
