package Ubenu.bean;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondConfiguration {
	
	@Bean(name= {"appMemory"},
			initMethod="init", destroyMethod="destroy")
	public ApplicationMemory getApplicationMemory() {
		return new ApplicationMemory();
	}
	
	public class ApplicationMemory extends HashMap {
		
		@Override
		public String toString() {
			return "ApplicationMemory" + this.hashCode();
		}
		
		public void init() {
			System.out.println("init method called");
		}
		public void destroy() {
			System.out.println("destroy method called");
		}
	}

}
