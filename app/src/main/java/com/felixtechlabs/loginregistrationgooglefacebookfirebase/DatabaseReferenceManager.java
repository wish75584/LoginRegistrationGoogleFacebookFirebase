package com.felixtechlabs.loginregistrationgooglefacebookfirebase;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatabaseReferenceManager {

    private static DatabaseReferenceManager instance;

    private DatabaseReferenceManager() {

    }

    public static DatabaseReferenceManager getInstance() {
        if (instance == null) {
            instance = new DatabaseReferenceManager();
        }
        return instance;
    }

    private static class ReferenceKeys {
        final static String USERS = "users";
        final static String SMS_LIST = "sms_list";
    }

    private final static String ENVIRONMENT = "Development";

    private CollectionReference getDatabaseReference() {
        return FirebaseFirestore.getInstance().collection(ENVIRONMENT);
    }

    public CollectionReference getUserReference() {
        return getDatabaseReference().document(ReferenceKeys.USERS).collection(ReferenceKeys.USERS);
    }

    public CollectionReference getSMSListReference() {
        return  getDatabaseReference().document(ReferenceKeys.SMS_LIST).collection(ReferenceKeys.SMS_LIST);
    }
}

