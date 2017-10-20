package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria{
	
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;
	
	
	public TutoriaImpl(String s){
		//1. trocear la cadena
		String[] trozos = s.split(",");
		
		//2. checkear que el numero de valores es correcto
		if(trozos.length != 3){
			throw new IllegalArgumentException();
		}
		
		//3. hacer una copia de los valores que hay en esa cadena
		String letraSemana = trozos[0].trim();
		DayOfWeek diaDeSemana = null;
		
		if(letraSemana.equals("L")){
			diaDeSemana = DayOfWeek.MONDAY;
			
		}else if(letraSemana.equals("M")){
			diaDeSemana = DayOfWeek.TUESDAY;
			
		}else if(letraSemana.equals("X")){
			diaDeSemana = DayOfWeek.WEDNESDAY;
			
		}else if(letraSemana.equals("J")){
			diaDeSemana = DayOfWeek.THURSDAY;
			
		}else if(letraSemana.equals("V")){
			diaDeSemana = DayOfWeek.FRIDAY;
		} else{
			throw new ExcepcionTutoriaNoValida();
		}
		
		LocalTime horaComienzo = LocalTime.parse(trozos[1].trim());
		LocalTime horaFin = LocalTime.parse(trozos[2].trim());
		
		//4. chekear las restricciones del tipo
		checkDiaSemana(diaDeSemana);
		checkDuracion1(horaFin, horaComienzo);
		
		
		//5. copiar en los atributos
		this.diaSemana = diaDeSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}
	

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {
		checkDiaSemana(diaSemana);
		checkDuracion1(horaFin, horaComienzo);
		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}
	
	
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {
		checkDiaSemana(diaSemana);
		checkDuracion2(duracion);
		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaComienzo.plusMinutes(duracion);//calculo la hora fin: hora comienzo + la diracion en minutos
	}

	
	private void checkDiaSemana(DayOfWeek diaSemana){
		if((diaSemana == DayOfWeek.SATURDAY ) || (diaSemana == DayOfWeek.SUNDAY)){
			throw new ExcepcionTutoriaNoValida();
		}
	}
	
	private void checkDuracion2(Integer duracion){
		if(duracion < 15){
			throw new ExcepcionTutoriaNoValida();
		}
	}
	
	private void checkDuracion1(LocalTime horaFin, LocalTime horaComienzo){
		Integer fin = horaFin.getHour() * 60 + horaFin.getMinute();
		Integer inicio = horaComienzo.getHour() * 60 + horaComienzo.getMinute();
		if((fin - inicio) < 15){
			throw new ExcepcionTutoriaNoValida();
		}
	}

	@Override
	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}	
		
	@Override
	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}


	@Override
	public LocalTime getHoraFin() {
		return horaFin;
	}


	@Override
	public Integer getDuracion() {
		Integer fin = horaFin.getHour() * 60 + horaFin.getMinute();
		Integer inicio = horaComienzo.getHour() * 60 + horaComienzo.getMinute();
		return fin - inicio;
	}
	
	
	
	public boolean equals(Object o){
		boolean r = false;
		if(o instanceof Tutoria){
		Tutoria t = (Tutoria) o;
		r = this.getDiaSemana().equals(t.getDiaSemana()) && this.getHoraComienzo().equals(t.getHoraComienzo());
		}
		return r;
	}
	
	
	public int hashCode(){
		return getDiaSemana().hashCode() + getHoraComienzo().hashCode()*1;
	}
	
	
	public int compareTo(Tutoria t) {
		int r;
		if(t==null){
		throw new NullPointerException();
		}
		r = getDiaSemana().compareTo(t.getDiaSemana());
		if (r == 0) {
			r = getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return r;
	}
	
	
	
	public String toString(){
		
		DayOfWeek dia = getDiaSemana();
		String acronimo;
		
		if(dia==DayOfWeek.MONDAY){
			acronimo = "L";
			
		}else if(dia==DayOfWeek.TUESDAY){
			acronimo = "M";
			
		}else if(dia==DayOfWeek.WEDNESDAY){
			acronimo = "X";
			
		}else if(dia==DayOfWeek.THURSDAY){
			acronimo = "J";
			
		}else{
			acronimo = "V";
		}
		
		return acronimo + " " + horaComienzo + "-" + getHoraFin(); 
	}
	
	
	
	
	
	
	
	
	
}
