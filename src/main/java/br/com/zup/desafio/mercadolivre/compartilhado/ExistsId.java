package br.com.zup.desafio.mercadolivre.compartilhado;

import static java.lang.annotation.ElementType.FIELD;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {ExistsIdValidator.class})
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

    String message() default "Ops! Este ID não é válido ou não foi encontrado!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}
