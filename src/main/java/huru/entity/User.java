package huru.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Trivial JPA entity for vertx-spring demo
 */
@Entity
@Table(name="HURU_USER")
public class User {
  
  @Id
  @Column(name="ID")
  private Integer productId;
  
  @Column
  private String description;
  
  public Integer getProductId() {
    return this.productId;
  }
  
  public String getDescription() {
    return this.description;
  }
}
