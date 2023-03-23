package task.simpleShop.service.mapper;

import task.simpleShop.model.Feedback;
import task.simpleShop.model.Item;
import task.simpleShop.model.User;
import task.simpleShop.model.dto.FeedbackDto;

public class FeedbackMapper {

    public static FeedbackDto toFeedbackDto(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getId())
                .item(feedback.getItem().getId())
                .user(feedback.getUser().getId())
                .text(feedback.getText())
                .created(feedback.getCreated())
                .build();
    }

    public static Feedback toFeedback(FeedbackDto feedbackDto, Item item, User user) {
        return Feedback.builder()
                .id(feedbackDto.getId())
                .item(item)
                .user(user)
                .text(feedbackDto.getText())
                .created(feedbackDto.getCreated())
                .build();
    }
}
