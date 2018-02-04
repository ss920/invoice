package com.systena.invoice.validate;

import com.systena.invoice.validate.impl.DataCheckValidImp;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 * The Interface DateCheckValid.
 */
@Documented
@Constraint(validatedBy = {DataCheckValidImp.class})
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DateCheckValid {

    /**
     * Message.
     *
     * @return the string
     */
    String message() default "終了日は開始日より過去にはできません。";

    /**
     * Groups.
     *
     * @return the class[]
     */
    Class<?>[] groups() default {};

    /**
     * Payload.
     *
     * @return the class<? extends payload>[]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Start date property.
     *
     * @return the string
     */
    String startDateProperty();

    /**
     * End date property.
     *
     * @return the string
     */
    String endDateProperty();

    /**
     * The Interface List.
     */
    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {

        /**
         * Value.
         *
         * @return the date check valid[]
         */
        DateCheckValid[] value();
    }

}
