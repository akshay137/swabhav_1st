package com.techlabs.ttt;

public class UserFactory {
    private final static String[] names = {"abc", "xyz", "def"};
    
    public static User[] getUsers(int userCount)
    {
	User[] users = new User[userCount];
	MarkType[] marks = MarkType.values();
	for (int i = 0; i < userCount; i++)
	{
	    users[i] = new User(names[i], marks[i + 1], new Console());
	}
	return users;
    }
}
