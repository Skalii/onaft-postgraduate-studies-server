package skalii.restful.onaftpostgraduatestudiesserver


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["skalii.restful.onaftpostgraduatestudiesserver"])
class OnaftPostgraduateStudiesServerApplication

fun main(args: Array<String>) {
	runApplication<OnaftPostgraduateStudiesServerApplication>(*args)
}

//todo fixed all functions: fun(id.., name.., number.., ...) fun(id..) {id.. ?: ..Repository.findBy..(name.., number.., ...).id..}