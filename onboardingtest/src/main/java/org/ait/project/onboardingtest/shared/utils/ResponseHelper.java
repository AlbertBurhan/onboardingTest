package org.ait.project.onboardingtest.shared.utils;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.ait.project.onboardingtest.shared.constant.enums.ResponseEnum;
import org.ait.project.onboardingtest.shared.dto.template.PaginationConfig;
import org.ait.project.onboardingtest.shared.dto.template.ResponseCollection;
import org.ait.project.onboardingtest.shared.dto.template.ResponseDetail;
import org.ait.project.onboardingtest.shared.dto.template.ResponseError;
import org.ait.project.onboardingtest.shared.dto.template.ResponseList;
import org.ait.project.onboardingtest.shared.dto.template.ResponseSchema;
import org.ait.project.onboardingtest.shared.dto.template.ResponseTemplate;
import org.ait.project.onboardingtest.shared.transform.ResponseTemplateTransform;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseHelper {

  private final ResponseMessageHelper responseMessageHelper;

  private final ResponseTemplateTransform responseTransform;

  public <T> ResponseEntity<ResponseTemplate<ResponseDetail<T>>> createResponseDetail(
      ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateDetail(responseMessageHelper.getResponseSchema(responseEnum), body)
        );
  }

  public <T> ResponseEntity<ResponseTemplate<T>> createResponseError(
      ResponseEnum responseEnum, T body) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateError(responseMessageHelper.getResponseSchema(responseEnum), body)
        );
  }

  public <T> ResponseEntity<ResponseTemplate<ResponseList<T>>> createResponseCollection(
      ResponseEnum responseEnum, Page page,
      List<T> contents) {
    return ResponseEntity.status(responseEnum.getHttpStatus())
        .body(
            responseTransform.templateCollection(responseMessageHelper.getResponseSchema(responseEnum), page,contents)
        );
  }

  public Object createResponseErrorTemplate(ResponseEnum invalidParam, ResponseError responseError) {
    return responseTransform.templateError(responseMessageHelper.getResponseSchema(invalidParam), responseError);
  }
}
