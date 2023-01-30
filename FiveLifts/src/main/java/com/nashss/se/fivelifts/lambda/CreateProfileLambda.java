package com.nashss.se.fivelifts.lambda;

import com.nashss.se.fivelifts.activity.requests.CreateProfileRequest;
import com.nashss.se.fivelifts.activity.results.CreateProfileResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Takes a CreateProfileRequest and returns a LambdaResponse.
 */
public class CreateProfileLambda
        extends LambdaActivityRunner<CreateProfileRequest, CreateProfileResult>
         implements RequestHandler<AuthenticatedLambdaRequest<CreateProfileRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateProfileRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateProfileRequest unauthenticatedRequest = input.fromBody(CreateProfileRequest.class);
                return input.fromUserClaims(claims ->
                    CreateProfileRequest.builder()
                        .withBarbellRow(unauthenticatedRequest.getBarbellRow())
                        .withBench(unauthenticatedRequest.getBench())
                        .withDeadlift(unauthenticatedRequest.getDeadlift())
                        .withOverheadPress(unauthenticatedRequest.getOverheadPress())
                        .withSquat(unauthenticatedRequest.getSquat())
                        .withBodyWeight(unauthenticatedRequest.getBodyWeight())
                        .withName(claims.get("name"))
                        .withEmail(claims.get("email"))
                        .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateProfileActivity().handleRequest(request)
        );
    }
}
