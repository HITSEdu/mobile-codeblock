package hitsedu.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

val Context.projectDataStore: DataStore<String> by dataStore(
    fileName = "project",
    serializer = ProjectJsonSerializer,
)