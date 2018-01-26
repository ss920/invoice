package com.systena.invoice.validate.impl;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.systena.invoice.constant.Constant;
import com.systena.invoice.validate.DateCheckValid;

import lombok.extern.slf4j.Slf4j;

/**
 * 日付妥当性チェック実装クラス
 */
@Slf4j
public class DataCheckValidImp implements ConstraintValidator<DateCheckValid, Object> {

	private String startDateProperty;
	private String endDateProperty;
	private String message;

	/**
	 * 初期化処理
	 */
	@Override
	public void initialize(DateCheckValid constraintAnnotation) {
		this.startDateProperty = constraintAnnotation.startDateProperty();
		this.endDateProperty = constraintAnnotation.endDateProperty();
		this.message = constraintAnnotation.message();
	}

	/**
	 * 日付妥当性チェック処理<br>
	 * 開始日・終了日を受け取り、妥当性をチェックする<br>
	 * true: 妥当な日付<br>
	 * false: 開始日が終了日より過去である
	 */
	public boolean isValid(Object form, ConstraintValidatorContext context) {

		boolean ret = true;
		if(form == null){
			ret = true;
		}else{
			try {
				BeanWrapper beanWrapper = new BeanWrapperImpl(form);
				String start = StringUtils.replace(
						(String) beanWrapper.getPropertyValue(startDateProperty)
						, Constant.SLASH
						, Constant.HYPHEN);

				String end = StringUtils.replace(
						(String) beanWrapper.getPropertyValue(endDateProperty)
						, Constant.SLASH
						, Constant.HYPHEN);

				// 開始日時
				LocalDate startDateObj = LocalDate.parse(start);
				// 終了日時
				LocalDate endDateObj = LocalDate.parse(end);

				if(endDateObj.compareTo(startDateObj) >= 0){
					ret = true;
				}else{
					ret = false;
				}
			}
			catch( Exception e ) {
				// ログ出力だけ行い、結果はtrueを返却する
				log.error("[ERROR] : "+ e);
				ret = true;
			}
		}

		if(ret){
			return true;
		}else{
			context.disableDefaultConstraintViolation();
			context
				.buildConstraintViolationWithTemplate(message)
				.addPropertyNode(endDateProperty)
				.addConstraintViolation();
			return false;
		}
	}
}
