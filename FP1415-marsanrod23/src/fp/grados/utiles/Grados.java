package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;


public class Grados {
	
	private static Boolean setUsarJava8 = true;//b12
	
	public static <T> List<T> leeFichero(String nombreFichero, Function<String,T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero))
					 .map(funcion_deString_aT)
					 .collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error en lectura del fichero: "+nombreFichero);
		}
		
		return res;
	}
	
	
									 /*******GRADO******/
	private static Set<Grado> gradosCreados = new HashSet<Grado>();
	
	private static void actualizarPobGrados(Grado res){
		gradosCreados.add(res);
	}
	
	// por parametros
		public static Grado createGrado(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
				Double numeroMinimoCreditosOptativas) {
			/*// crear variable tipo de retporno
			Grado res;
			// usar el constructor que tome
			res = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
			// actualizar las propiedades poblacionales
			actualizarPobGrados(res);
			// devolver el objeto
			return res;*/
			Grado res;
			if(setUsarJava8){
				res = new GradoImpl2(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
			}else{
				res = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
			}
			actualizarPobGrados(res);
			return res;
		}
		
	public static Integer getNumGradosCreados() {
		return gradosCreados.size();
	}

	public static Set<Grado> getGradosCreados() {
		return new HashSet<Grado>(gradosCreados);
	}
	
	//número medio de asignaturas de cualquier tipo
	public static Double getMediaAsignaturasGrados() {
		Double res = 0.;
		Double asignaturasTotales = 0.;
		if (gradosCreados == null) {
			res = 0.;
		} else {
			for (Grado g : gradosCreados) {
				asignaturasTotales = asignaturasTotales
						+ g.getAsignaturasObligatorias().size()
						+ g.getAsignaturasOptativas().size();
			}
			res = 1.0* (asignaturasTotales / getNumGradosCreados());
		}
		return res;
	}
	
	//número medio de asignaturas obligatorias
	public static Double getMediaAsignaturasObligatoriasGrados(){
		Double res = null;
		Double asignaturasObligatorias = 0.;
		for(Grado g : gradosCreados){
			asignaturasObligatorias = asignaturasObligatorias + g.getAsignaturasObligatorias().size() ;
		}
		res = asignaturasObligatorias / getNumGradosCreados();
		
		return res;
	}
	
	//número medio de asignaturas optativas
	public static Double getMediaAsignaturasOptativasGrados(){
		Double res = null;
		Double asignaturasOptativas = 0.;
		for(Grado g : gradosCreados){
			asignaturasOptativas = asignaturasOptativas + g.getAsignaturasOptativas().size() ;
		}
		res = asignaturasOptativas / getNumGradosCreados();
		
		return res;
	}
	
	
	
	
									/*******ESPACIO******/
	private static SortedSet<Espacio> espaciosCreados = new TreeSet<Espacio>();
	
	private static void actualizarPobEspacios(Espacio res){
		espaciosCreados.add(res);
	}
	
	// por parametros
	public static Espacio createEspacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		// crear variable tipo de retporno
		Espacio res;
		// usar el constructor que tome
		res = new EspacioImpl(tipo, nombre, capacidad, planta);
		// actualizar las propiedades poblacionales
		actualizarPobEspacios(res);
		// devolver el objeto
		return res;
	}
		
	// copia
	public static Espacio createEspacio(Espacio e) {
		return createEspacio(e.getTipo(), e.getNombre(), e.getCapacidad(), e.getPlanta());
	}

	// por String
	public static Espacio createEspacio(String s) {
		// crear variable tipo de retporno: res
		// usar el constructor que tome
		Espacio res = new EspacioImpl(s);
		// actualizar las propiedades poblacionales
		actualizarPobEspacios(res);
		// devolver el objeto
		return res;
	}

	// por fichero
	public static List<Espacio> createEspacios(String file) {
		List<Espacio> res = leeFichero(file, s -> createEspacio(s));
		return res;
	}

	public static Integer getNumEspaciosCreados() {
		return espaciosCreados.size();
	}

	public static SortedSet<Espacio> getEspaciosCreados() {
		return new TreeSet<Espacio>(espaciosCreados);
	}
	
	//número de la planta máxima
	public static Integer getPlantaMayorEspacio() {
		Integer res = -10000;
		Integer ac;
		if (espaciosCreados.isEmpty()) {
			res = null;
		} else {
			for (Espacio e : espaciosCreados) {
				ac = e.getPlanta();
				if (ac > res) {
					res = ac;
				}
			}
		}

		return res;
	}
	
	//número de la planta mínima
	public static Integer getPlantaMenorEspacio() {
		Integer res = 10000;
		Integer ac;
		if (espaciosCreados.isEmpty()) {
			res = null;
		} else {
			for (Espacio e : espaciosCreados) {
				ac = e.getPlanta();
				if (ac < res) {
					res = ac;
				}
			}
		}

		return res;
	}
	
	
	
									/*******DESPACHO******/
	private static Set<Despacho> despachosCreados = new HashSet<Despacho>();
	
	private static void actualizarPobDespachos(Despacho res){
		despachosCreados.add(res);
	}
	
	// por parametros
	public static Despacho createDespacho(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		// crear variable tipo de retporno
		Despacho res;
		// usar el constructor que tome
		res = new DespachoImpl(nombre, capacidad, planta, profesores);
		// actualizar las propiedades poblacionales
		actualizarPobDespachos(res);
		// devolver el objeto
		return res;
	}
	
	// copia
	public static Despacho createDespacho(Despacho c) {
		return createDespacho(c.getNombre(), c.getCapacidad(), c.getPlanta(), c.getProfesores());
	}
	
	// por String
	public static Despacho createDespacho(String s) {
		// crear variable tipo de retporno: res
		// usar el constructor que tome
		Despacho res = new DespachoImpl(s);
		// actualizar las propiedades poblacionales
		actualizarPobDespachos(res);
		// devolver el objeto
		return res;
	}

	// por fichero
	public static List<Despacho> createDespachos(String file) {
		List<Despacho> res = leeFichero(file, s -> createDespacho(s));
		return res;
	}
	
	
	
	
	
									/*******CENTRO******/
	private static Set<Centro> centrosCreados = new HashSet<Centro>();
	
	private static void actualizarPobCentros(Centro res){
		centrosCreados.add(res);
	}
	
	//por parametros
	public static Centro createCentro(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos){
		/*//crear variable tipo de retporno
		Centro res;
		//usar el constructor que tome
		res = new CentroImpl(nombre, direccion, numeroPlantas, numeroSotanos);
		//actualizar las propiedades poblacionales
		actualizarPobCentros(res);
		//devolver el objeto	
		return res;*/
		Centro res;
		if(setUsarJava8){
			res = new CentroImpl2(nombre, direccion, numeroPlantas, numeroSotanos);
		}else{
			res = new CentroImpl(nombre, direccion, numeroPlantas, numeroSotanos);
		}
		actualizarPobCentros(res);
		return res;
	}
	
	// copia
	public static Centro createCentro(Centro c) {
		
		Centro res;
		if(setUsarJava8){
			res = new CentroImpl2(c.getNombre(), c.getDireccion(), c.getNumeroPlantas(), c.getNumeroSotanos());
			for(Espacio e : c.getEspacios()){
				res.nuevoEspacio(e);
			}
			actualizarPobCentros(res);
		}else{
			res = new CentroImpl(c.getNombre(), c.getDireccion(), c.getNumeroPlantas(), c.getNumeroSotanos());
			for(Espacio e : c.getEspacios()){
				res.nuevoEspacio(e);
			}
			actualizarPobCentros(res);
		}
		return res;
	}
	
	public static Integer getNumCentrosCreados(){
		return centrosCreados.size();
	}
	
	public static Set<Centro> getCentrosCreados(){
		return new HashSet<Centro>(centrosCreados);
	}
	
	//número máximo de plantas
	public static Integer getMaxPlantas() {
		Integer res = 0;
		if (getNumCentrosCreados() == null) {
			res = null;
		} else {
			for (Centro c : centrosCreados) {
				if (c.getNumeroPlantas() > res) {
					res = c.getNumeroPlantas();
				}
			}
		}

		return res;
	}
	
	
	//número máximo de sótanos
	public static Integer getMaxSotanos(){
		Integer res = 0;
		for(Centro c : centrosCreados){
			if(c.getNumeroSotanos() > res){
				res = c.getNumeroSotanos();
			}
		}
		
		return res;
	}
	
	// número medio de plantas
	public static Double getMediaPlantas() {
		Double res = 0.;
		Double plantasTotales = 0.;
			for (Centro c : centrosCreados) {
				plantasTotales += 1.0 * c.getNumeroPlantas();
			}
			if(centrosCreados.size() != 0){
				res = 1.0 * (plantasTotales / centrosCreados.size());
			}
		return res;
	}
	
	//número medio de sótanos
	public static Double getMediaSotanos(){
		Double res = 0.;
		Double sotanosTotales = 0.;
		Double numCentros = 0.;
		for(Centro c : centrosCreados){
			sotanosTotales = sotanosTotales + c.getNumeroSotanos();
			numCentros = numCentros + 1;
		}
		res = (sotanosTotales / numCentros);
		
		return res;
	}
	
	
	
										/*******ALUMNO******/
	private static Set<Alumno> alumnosCreados = new HashSet<Alumno>();
	
	
	private static void actualizarPobAlumnos(Alumno res){
		alumnosCreados.add(res);
	}
	
	//por parametros
	public static Alumno createAlumno(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email){
		/*//crear variable tipo de retporno
		Alumno res;
		//usar el constructor que tome
		res = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
		//actualizar las propiedades poblacionales
		actualizarPobAlumnos(res);
		//devolver el objeto	
		return res;*/
		Alumno res;
		if(setUsarJava8){
			res = new AlumnoImpl2(dni, nombre, apellidos, fechaNacimiento, email);
		}else{
			res = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
		}
		actualizarPobAlumnos(res);
		return res;
	}
	
	
	// por String
	public static Alumno createAlumno(String s) {
		// crear variable tipo de retorno: res
		// usar el constructor que tome
		Alumno res ;
		if(setUsarJava8){
			res = new AlumnoImpl2(s);
		}else{
			res = new AlumnoImpl(s);
		}
		// actualizar las propiedades poblacionales
		actualizarPobAlumnos(res);
		// devolver el objeto
		return res;
	}
	
	//copia
	public static Alumno createAlumno(Alumno a){
		Alumno al;
		al = createAlumno(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
		for(Asignatura as : a.getAsignaturas()){
			al.matriculaAsignatura(as);
		}
		for(Nota n : a.getExpediente().getNotas()){
			al.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(), n.getConvocatoria(), n.getValor());
		}
		
		return al;
	}
	
	// por fichero
	public static List<Alumno> createAlumnos(String file) {
		List<Alumno> res = leeFichero(file, s -> createAlumno(s));
		return res;
	}
	
	public static Integer getNumAlumnosCreados(){
		return alumnosCreados.size();
	}
	
	public static Set<Alumno> getAlumnosCreados(){
		return new HashSet<Alumno>(alumnosCreados);
	}
	
	

										/*******PROFESOR******/
	private static Set<Profesor> profesoresCreados = new HashSet<Profesor>();
	public static Boolean usarImplementacionMapProfesor = true;
	
	private static void actualizarPobProfesor(Profesor res) {
		profesoresCreados.add(res);
		
	}
	
	//copia
	public static Profesor createProfesor(Profesor p){
		Profesor prof;
			prof = createProfesor(p.getDNI(), p.getNombre(), p.getApellidos(), p.getFechaNacimiento(), p.getEmail(), p.getCategoria(),
				p.getDepartamento());
			
			for(Tutoria t : p.getTutorias()){
				prof.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
			}
			for(Asignatura a : p.getAsignaturas()){
				prof.imparteAsignatura(a, p.dedicacionAsignatura(a));
			}
		return prof;
	}
	
	//por parametros
	public static Profesor createProfesor(String dni, String nombre, String apellidos, LocalDate fecha, String email, Categoria categoria, Departamento dpto){
		Profesor res;
		if(usarImplementacionMapProfesor){
			res = new ProfesorImpl2(dni, nombre, apellidos, fecha, email, categoria, dpto);
		}else{
			res = new ProfesorImpl(dni, nombre, apellidos, fecha, email, categoria, dpto);
		}
		actualizarPobProfesor(res);
		return res;
	}
	
	
	public static Integer getNumProfesoresCreados(){
		return profesoresCreados.size();
	}
	
	public static Set<Profesor> getProfesoresCreados(){
		return new HashSet<Profesor>(profesoresCreados);
	}
	
	public static void setUsarImplementacionMapProfesor(Boolean b){
		usarImplementacionMapProfesor = b;
	}
	
	

	
	
	
										/*******DEPARTAMENTO******/
	private static Set<Departamento> departamentosCreados = new HashSet<Departamento>();
	
	private static void actualizarPobDepartamentos(Departamento res){
		departamentosCreados.add(res);
	}
	
	//por parametros
	public static Departamento createDepartamento(String nombre){
		/*//crear variable tipo de retporno: res
		//usar el constructor que tome
		Departamento res = new DepartamentoImpl(nombre);
		//actualizar las propiedades poblacionales
		actulaizaPobDepartamentos(res);
		//devolver el objeto
		return res;*/
		Departamento res;
		if(setUsarJava8){
			res = new DepartamentoImpl2(nombre);
		}else{
			res = new DepartamentoImpl(nombre);
		}
		actualizarPobDepartamentos(res);
		return res;
	}
	
	public static Integer getNumDepartamentosCreados(){
		return departamentosCreados.size();
	}
	
	public static Set<Departamento> getDepartamentosCreados(){
		return new HashSet<Departamento>(departamentosCreados);
	}
	
	
	
												/*******BECA******/
	private static Set<Beca> becasCreadas = new HashSet<Beca>();

	public static void actualizarPobBeca(Beca res){
		becasCreadas.add(res);
	}
	
	//copia
	public static Beca createBeca(Beca original){
		return createBeca(original.getCodigo(), original.getCuantiaTotal(), original.getDuracion(), original.getTipo());
	}
	

	//por parametros 1
		public static Beca createBeca(String codigo, Double cuantia, Integer duracion, TipoBeca tipo){
			Beca res = new BecaImpl(codigo, cuantia, duracion, tipo);
			actualizarPobBeca(res);
			return res;
		}
	
	
	//por parametros 2
	public static Beca createBeca(String codigo, TipoBeca tipo){
		Beca res = new BecaImpl(codigo, tipo);
		actualizarPobBeca(res);
		return res;
	}
	
	//por fichero
	public static List<Beca> createBecas(String file){
		List<Beca> res = leeFichero(file, s -> createBeca(s));
		return res;
	}
	
	//por String
	public static Beca createBeca(String s){
		//crear variable tipo de retorno
		Beca res;
		//usar el constructor que tome
		res = new BecaImpl(s);
		//actualizar las propiedades poblacionales
		actualizarPobBeca(res);
		//devolver el objeto
		return res;
	}
	
	public static Integer getNumBecasTipo(TipoBeca t){
		Integer res = 0;
		for(Beca b : getBecasCreadas()){
		if(b.getTipo() == t){
			res++;
		}
		
		/*	switch(b.getTipo()){
			case ORDINARIA:
				break;
			case MOVILIDAD:
				break;
			case EMPRESA:
				res[2]++;
				break;
				default:
			}*/
		}
		return res;
	}
	
	public static Set<Beca> getBecasCreadas(){
		return new HashSet<Beca>(becasCreadas);
	}
	
	public static Integer getNumBecasCreadas(){
		return becasCreadas.size();
	}
	
	
	
										/*******ASIGNATURA******/
	private static Map<String, Asignatura> asignaturasCreadas = new HashMap<String, Asignatura>();
	
	public static void actualizarPobAsignaturas(Asignatura res){
		asignaturasCreadas.put(res.getCodigo(), res);
	}
	
	//por fichero
	public static List<Asignatura> createAsignaturas(String file){
		List<Asignatura> res = leeFichero(file, s-> createAsignatura(s));
		return res;
	}
	
	//por parametros
	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso, Departamento departamento){
		//crear variable tipo de retporno
		Asignatura res;
		//usar el constructor que tome
		res = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
		//actualizar las propiedades poblacionales
		actualizarPobAsignaturas(res);
		//devolver el objeto	
		return res;
	}
	
	//por String
	public static Asignatura createAsignatura(String s){
		//crear variable tipo de retporno
		Asignatura res;
		//usar el constructor que tome
		res = new AsignaturaImpl(s);
		//actualizar las propiedades poblacionales
		actualizarPobAsignaturas(res);
		//devolver el objeto	
		return res;
	}
	
	public static Asignatura getAsignaturaCreada(String codigo){
		if(codigo == null){
			return null;
		}else{
			return asignaturasCreadas.get(codigo);
		}
	}
	
	
	public static Set<String> getCodigosAsignaturasCreadas(){
		return new HashSet<String>(asignaturasCreadas.keySet());
	} 
	
	public static Integer getNumAsignaturasCreadas(){
		return asignaturasCreadas.size();
	}
	
	
	public static Set<Asignatura> getAsignaturasCreadas(){
		return new HashSet<Asignatura>(asignaturasCreadas.values());
	} 
	

	
	/*******--------------------------------------------------------------------------------------------------------------******/
	
	// b12
	public static void setUsarJava8(Boolean b) {
		setUsarJava8 = b;
	}

	
	
	
}
