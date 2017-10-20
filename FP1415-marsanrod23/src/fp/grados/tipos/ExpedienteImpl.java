package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;



public class ExpedienteImpl implements Expediente{

	private List<Nota> notas;
	
	
										/******** CONSTRUCTOR******/
	public ExpedienteImpl(){
		this.notas = new ArrayList<Nota>();
	}
	
	
										/******** RESTRICCIONES******/
	
	//b8
	//un expediente no puede contener notas para más de dos convocatorias de una misma asignatura y curso.
	//Si no se cumple esta restricción, lance la excepción ExcepcionExpedienteOperacionNoPermitida.
	private void checkNotas(Nota notas){

		Integer contador = 0;
		for(Nota n : getNotas()){
				if(notas.getCursoAcademico().equals(n.getCursoAcademico()) && notas.getAsignatura().equals(n.getAsignatura())){
					contador++;
						
			}
			if(contador > 2){
				contador =2;
				throw new ExcepcionExpedienteOperacionNoPermitida();
			}
		}
	}
	
	
	
										  /******** METODOS ******/
	//Añade la nota n al expediente. Si en el expediente ya existe una nota para la misma asignatura, convocatoria
		//y curso académico (es decir, otra nota igual según el criterio de igualdad del tipo Nota), 
		//se elimina la nota antigua y se añade la nueva. Las notas se añaden siempre al final de la lista.
		public void nuevaNota(Nota n) {
			Integer index = notas.indexOf(n);
			if(index.equals(-1)){
				notas.add(n);
			}else{
				notas.remove(getNotas().get(index));
				notas.add(n);
			}
			checkNotas(n);
		}
	
	
	
	 									  /******** GET & SET ******/
	@Override
	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}
		

	
	//b8
	//La nota media de un expediente es la media de los valores numéricos de todas las notas del expediente que tienen 
	//un valor mayor o igual a 5. Si el expediente está vacío, la nota media será 0.0.
	public Double getNotaMedia() {
		Double SumaNotasMayorCinco = 0.;
		Double res = 0.;
		Double contadorAsignaturas = 0.;
		
		for (Nota n : getNotas()){
			if(n.getValor() >= 5){
				SumaNotasMayorCinco = SumaNotasMayorCinco + n.getValor();
				contadorAsignaturas = contadorAsignaturas + 1.;
			}
		}
		if (SumaNotasMayorCinco == 0.){
			res = 0.;
		}else{
			res = SumaNotasMayorCinco / contadorAsignaturas;
		}
		return res;
	}
	
	
	
	 									/******** EQUALS... ******/
	//dos expedientes son iguales si tienen las mismas notas y están en el mismo orden
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Expediente){
		Expediente e = (Expediente) o;
		r = this.getNotas().equals(e.getNotas());
		}
		return r;
	}
	
	public int hashCode(){
		return getNotas().hashCode();
	}
	
											/******** toString ******/
	public String toString(){
		return getNotas().toString();
	}


	//b12 Devuelve una lista con las notas del expediente ordenadas por asignatura y, a igualdad de asignatura, por su orden natural.
	public List<Nota> getNotasOrdenadasPorAsignatura() {
		Comparator<Nota> cmp = Comparator.comparing(Nota::getAsignatura).thenComparing(Comparator.naturalOrder());
		
		List<Nota> res = new ArrayList<Nota>();
		res.addAll(getNotas());
		res.sort(cmp);
		return res;
	}


	//b12 Devuelve la mejor nota del expediente. Se considera la mejor nota aquella que tenga matrícula de honor.
	//En caso de haber varias con matrícula de honor (o no haber ninguna), se devuelve la que tenga el mayor valor. 
	//A su vez, si hay varias con el mismo valor, se devuelve la obtenida en una convocatoria anterior, y si aún hay varias, 
	//la obtenida en el menor curso. Si el expediente no contiene ninguna nota, lance la excepción NoSuchElementException.
	public Nota getMejorNota() {
		List<Nota> mejorNota = getNotas();
		Comparator<Nota> cmp = Comparator.comparing(Nota::getMencionHonor)
				.thenComparing(Nota::getValor)
				.thenComparing(Comparator.comparing(Nota::getConvocatoria).reversed())
				.thenComparing(Comparator.comparing(Nota::getCursoAcademico).reversed());
		Predicate<Nota> predicado = x -> x != null;
		return mejorNota.stream().filter(predicado).max(cmp).get();
	}


									







	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
