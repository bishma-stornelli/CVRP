package tests;

import cvrp.classes.CustomerRouteTabu;
import cvrp.classes.CustomerRouteTabuPosition;
import cvrp.classes.CustomerTabu;
import cvrp.interfaces.Tabu;
import org.junit.*;

/**
 * @version 1.0
 * @author Bishma Stornelli
 * @author Vicente Santacoloma
 */
public class TabusEquality {
    
  public TabusEquality() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }
  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
  @Test
  public void equality() {
      Tabu ct = new CustomerTabu(1);
      Tabu crt = new CustomerRouteTabu(2, 1);
      Tabu crpt = new CustomerRouteTabuPosition(1, 2, 2, 1);
      assert ct.equals(crt);
      assert crt.equals(ct);
      assert crpt.equals(ct);
      assert ct.equals(crpt);
      assert crt.equals(crpt);
      assert crpt.equals(crt);

  }

  @Test
  public void disequality() {
      Tabu ct = new CustomerTabu(1);
      Tabu crt = new CustomerRouteTabu(3, 2);
      Tabu crpt = new CustomerRouteTabuPosition(1, 2, 2, 2);
      assert !ct.equals(crt);
      assert !crt.equals(ct);
      assert !crpt.equals(ct);
      assert !ct.equals(crpt);
      assert !crt.equals(crpt);
      assert !crpt.equals(crt);
  }
  
}
