package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestQuestion {
  
  private User user;
  
  @Before
  public void initialize(){
      this.user = Mockito.mock(User.class);
  }
  
  @Test
  public void test_it_can_be_created() throws DomainException {
    int id = 1337;
    User user = this.user;
    String text = "Waarvoor wordt testing gebruikt?";
    boolean approved = false;
    boolean reviewed = false;
    boolean removed = false;
    
    Question question = this.createQuestion(id, user, text, approved, reviewed, removed);
    
    assertNotNull(question);
    assertEquals(id, question.getId());
    assertEquals(user, question.getUser());
    assertEquals(text, question.getText());
    assertEquals(approved, question.isApproved());
    assertEquals(reviewed, question.isReviewed());
    assertEquals(removed, question.isRemoved());
  }
  
  @Test(expected = DomainException.class)
  public void test_it_requires_a_questionString() throws DomainException {
    this.createQuestion(1337, this.user, "", false, false, false);

  }
  
  @Test(expected = DomainException.class)
  public void test_it_if_approved_it_has_to_be_reviewed() throws DomainException {
      this.createQuestion(1337, this.user, "Waarvoor wordt testing gebruikt?", true, false, false);
  }
  
  @Test
  public void test_it_can_be_reviewed() throws DomainException {
    Question question = null;
    question = this.createQuestion(1337, this.user, "Waarvoor wordt testing gebruikt?", false, false, false);

    
    System.out.println(question);
    
    assertNotNull(question);
    assertFalse(question.isApproved());
    
    // Let's approve the question
    question.approve();
    
    assertTrue(question.isApproved());
    
    // NVM, let's disapprove it
    question.disapprove();
    
    assertFalse(question.isApproved());
    
    //When we approve or disapprove something, we reviewed it.
    assertTrue(question.isReviewed());
  }
  
  private Question createQuestion(int id,User user, String text, boolean approved, boolean reviewed, boolean removed) throws DomainException {
   return new Question(id,user,text,approved,reviewed,removed);
  }

}
