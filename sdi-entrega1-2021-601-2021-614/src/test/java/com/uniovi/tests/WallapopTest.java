package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WallapopTest {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\laura\\Escritorio\\Uni\\3-Uni\\2Semestre\\SDI\\LAB\\Sesion05\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	} /* Resto del código de la clase */

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before()
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		PO_PrivateView.login(driver, "admin@email.com", "admin");
	}

	// Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		PO_PrivateView.login(driver, "Javi@gmail.com", "123456");
	}

	// Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos)
	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, " ", "123456");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
		PO_LoginView.fillForm(driver, "Jose@gmail.com", " ");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
		PO_LoginView.fillForm(driver, " ", " ");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}

	// Inicio de sesión con datos válidos (usuario estándar, email existente, pero
	// contraseña incorrecta).
	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "Jose@gmail.com", "123");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}

	// Inicio de sesión con datos inválidos (usuario estándar, email no existente en
	// la aplicación).
	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "Joselito@gmail.com", "123456");
		PO_RegisterView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}
}
