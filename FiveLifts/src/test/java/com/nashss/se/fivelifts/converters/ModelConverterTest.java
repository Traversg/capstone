package com.nashss.se.fivelifts.converters;

import com.nashss.se.fivelifts.dynamodb.models.User;
import com.nashss.se.fivelifts.models.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toUserModel_withAllData_convertsUser() {
        User user = new User();
        user.setId("id");
        user.setName("name");
        user.setEmail("email");
        user.setBodyWeight(175.0);
        user.setSquat(225);
        user.setBench(175);
        user.setDeadlift(275);
        user.setOverheadPress(125);
        user.setBarbellRow(150);

        UserModel userModel = modelConverter.toUserModel(user);
        assertEquals(user.getId(), userModel.getId());
        assertEquals(user.getName(), userModel.getName());
        assertEquals(user.getEmail(), userModel.getEmail());
        assertEquals(user.getBodyWeight(), userModel.getBodyWeight());
        assertEquals(user.getSquat(), userModel.getSquat());
        assertEquals(user.getBench(), userModel.getBench());
        assertEquals(user.getDeadlift(), userModel.getDeadlift());
        assertEquals(user.getOverheadPress(), userModel.getOverheadPress());
        assertEquals(user.getBarbellRow(), userModel.getBarbellRow());
    }

}
