package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Sensor;
import com.boot.repository.SensorRepository;
import java.util.Iterator;


@RestController
@RequestMapping("/sensor")
public class SensorController {
	
	@Autowired
	private SensorRepository sensorRepository;
	
	@GetMapping("/todos")
	public List<Sensor> dados(){
		
		List<Sensor> listaDados = sensorRepository.findAll();;
		 
		return listaDados;
	}
	
	
	
	@GetMapping("/sensorHumidade/{value}")
	public List<Sensor> execute(@PathVariable(value = "value") int value){
		
	List<Sensor> listaHumidade = sensorRepository.findByNameAndValue("sensor humidade", value );
		 
		return listaHumidade;
	}
	
	@GetMapping("/ordem/{id}")
	public String[] ordem(@PathVariable(value="id") int id){
		//List<Sensor> listaOrdem = sensorRepository.findByNameLikeOrderByISODATADesc("sensor humidade");
		List<Sensor> listaHumidade = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor humidade");
		List<Sensor> listaTemperatura = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor temperatura");
		List<Sensor> listaPermanencia = sensorRepository.findByIdInsuranceAndNameOrderByISODATADesc(id, "sensor permanência");
		String[] value = {listaHumidade.get(0).getValue(), listaTemperatura.get(0).getValue(), listaPermanencia.get(0).getValue()};
		
		return value;
	}
	/*public void todos() {
		System.out.println("sensor found with findAll():");
		System.out.println("-------------------------------");
		for (sensor sensor : repository.findAll()) {
			System.out.println(sensor);
		}
		
	}
	*/

}