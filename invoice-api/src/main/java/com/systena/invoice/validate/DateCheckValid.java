package com.systena.invoice.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.systena.invoice.validate.impl.DataCheckValidImp;

/**
 * 日付妥当性チェック<br>
 * 開始日・終了日を受け取り、妥当性をチェックする<br>
 * true: 妥当な日付<br>
 * false: 開始日が終了日より過去である
 */
@Documented
@Constraint(validatedBy={DataCheckValidImp.class})
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DateCheckValid {

	String message() default "終了日は開始日より過去にはできません。";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	String startDateProperty();
	String endDateProperty();

	@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface List {
		DateCheckValid[] value();
	}

}
