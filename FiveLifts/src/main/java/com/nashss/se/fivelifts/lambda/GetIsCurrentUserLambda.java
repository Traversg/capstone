package com.nashss.se.fivelifts.lambda;

import com.nashss.se.fivelifts.activity.requests.GetIsCurrentUserRequest;
import com.nashss.se.fivelifts.activity.results.GetIsCurrentUserResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Takes a GetIsCurrentRequest and returns a LambdaResponse.
 */
public class GetIsCurrentUserLambda
    extends LambdaActivityRunner<GetIsCurrentUserRequest, GetIsCurrentUserResult>
    implements RequestHandler<AuthenticatedLambdaRequest<GetIsCurrentUserRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetIsCurrentUserRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    GetIsCurrentUserRequest.builder()
                        .withEmail(claims.get("email"))
                        .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetIsCurrentUserActivity().handleRequest(request)
        );
    }
}
