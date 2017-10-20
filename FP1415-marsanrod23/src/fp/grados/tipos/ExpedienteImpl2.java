package fp.grados.tipos;

import java.util.OptionalDouble;

public class ExpedienteImpl2 extends ExpedienteImpl implements Expediente{

	public ExpedienteImpl2() {
		super();
	}
	
	
	//b13
	//La nota media de un expediente es la media de los valores numéricos de todas las notas del expediente que tienen 
	//un valor mayor o igual a 5. Si el expediente está vacío, la nota media será 0.0.
	public Double getNotaMedia(){
		OptionalDouble res = getNotas().stream().filter(n -> n.getValor() >=5).mapToDouble(n -> n.getValor()).average();//average: hace la media directamente
		
		if(res.isPresent()){
			return res.getAsDouble();
		}else{
			return 0.0;
		}
	
	}
	

}
