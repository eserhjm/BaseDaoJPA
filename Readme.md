# BaseDao for JPA
### example
- You can use it like
```
public class UserDaoImpl extends BaseDaoImpl<User>implements UserDao{
	public UserDaoImpl() {
		super(User.class);
	}
}

public interface UserDao extends BaseDao<User>{

}
```
