package com.systena.invoice.validate.impl;

import com.systena.invoice.constant.Constant;
import com.systena.invoice.validate.DateCheckValid;
import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * The Class DataCheckValidImp.
 */
@Slf4j
public class DataCheckValidImp implements ConstraintValidator<DateCheckValid, Object> {

    /** The start date property. */
    private String startDateProperty;

    /** The end date property. */
    private String endDateProperty;

    /** The message. */
    private String message;

    @Override
    public void initialize(DateCheckValid constraintAnnotation) {
        this.startDateProperty = constraintAnnotation.startDateProperty();
        this.endDateProperty = constraintAnnotation.endDateProperty();
        this.message = constraintAnnotation.message();
    }

    /**
     * isValid method.
     */
    public boolean isValid(final Object form, final ConstraintValidatorContext context) {

        boolean ret = true;
        if (form == null) {
            ret = true;
        } else {
            try {
                BeanWrapper beanWrapper = new BeanWrapperImpl(form);
                String start = StringUtils.replace(
                        (String) beanWrapper.getPropertyValue(startDateProperty),
                        Constant.SLASH,
                        Constant.HYPHEN);

                String end = StringUtils.replace(
                        (String) beanWrapper.getPropertyValue(endDateProperty),
                        Constant.SLASH,
                        Constant.HYPHEN);

                // 開始日時
                LocalDate startDateObj = LocalDate.parse(start);
                // 終了日時
                LocalDate endDateObj = LocalDate.parse(end);

                if (endDateObj.compareTo(startDateObj) >= 0) {
                    ret = true;
                } else {
                    ret = false;
                }
            } catch (Exception e) {
                // ログ出力だけ行い、結果はtrueを返却する
                log.error("[ERROR] : " + e);
                ret = true;
            }
        }

        if (ret) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context
            .buildConstraintViolationWithTemplate(message)
            .addPropertyNode(endDateProperty)
                .addConstraintViolation();
            return false;
        }
    }
}
