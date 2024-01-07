package com.example.shop.shop.mapping;

import com.example.shop.shop.model.entity.Opinion;
import com.example.shop.shop.model.entity.User;
import com.example.shop.shop.model.request.OpinionRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpinionMapper implements Mapper<Opinion, OpinionRequest> {

    @Override
    public OpinionRequest convert(Opinion from) {
        return OpinionRequest.builder()
                .score(from.getScore())
                .text(from.getText())
                .build();
    }

    @Override
    public Opinion reverse(OpinionRequest from) {
        return Opinion.builder()
                .score(from.getScore())
                .text(from.getText())
                .build();
    }

    public Opinion reverseSave(OpinionRequest from, User user) {
        return Opinion.builder()
                .score(from.getScore())
                .text(from.getText())
                .user(user)
                .build();
    }

    public List<OpinionRequest> convertList(List<Opinion> opinions) {
        List<OpinionRequest> opinionsRequest = new ArrayList<>();
        for (Opinion opinion : opinions) {
            opinionsRequest.add(convert(opinion));
        }
        return opinionsRequest;
    }
}
