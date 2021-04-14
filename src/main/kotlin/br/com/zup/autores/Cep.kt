package br.com.zup.autores

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import javax.inject.Singleton
import javax.validation.Constraint
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import kotlin.annotation.AnnotationTarget.*
import kotlin.annotation.AnnotationRetention.RUNTIME

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [CepValidator::class])
annotation class Cep(
    val message: String = "Cep com formato invalido!"
)

@Singleton
class CepValidator: ConstraintValidator<Cep, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Cep>,
        context: ConstraintValidatorContext
    ): Boolean {
        if(value == null) return true

        return value.matches("^\\d{5}-\\d{3}\$".toRegex())
    }
}
