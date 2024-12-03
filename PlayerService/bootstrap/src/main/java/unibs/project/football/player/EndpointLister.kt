package unibs.project.football.player


// Spring 3
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Component
class EndpointLister : ApplicationListener<ContextRefreshedEvent> {

	override fun onApplicationEvent(event: ContextRefreshedEvent) {
		val applicationContext = event.applicationContext
		val requestMappingHandlerMapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping::class.java)
		val handlerMethods = requestMappingHandlerMapping.handlerMethods

		println("######## ENDPOINT LIST #############")
		handlerMethods.forEach { (requestMappingInfo, handlerMethod) ->
			val patterns = requestMappingInfo.pathPatternsCondition?.patterns
			val methods = requestMappingInfo.methodsCondition.methods
			val className = handlerMethod.beanType.name
			val methodName = handlerMethod.method.name

			patterns?.forEach { pattern ->
				methods.forEach { method ->
					println("$method $pattern $className::$method")
				}
			}
		}
		println("####################################")
	}
}


