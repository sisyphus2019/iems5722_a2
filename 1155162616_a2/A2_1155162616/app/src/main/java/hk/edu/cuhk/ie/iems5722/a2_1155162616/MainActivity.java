package hk.edu.cuhk.ie.iems5722.a2_1155162616;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//默认的activity
public class MainActivity extends AppCompatActivity{
    private List<ChatroomBean> chatrooms = new ArrayList<>();
    ListView chatroomListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取ListView对象
        chatroomListView = findViewById(R.id.chatroomListView);

        //用ShowChatroomAsyncTask执行更新聊天室列表
        ShowChatroomAsyncTask showChatrooms = new ShowChatroomAsyncTask(MainActivity.this,chatroomListView);
        showChatrooms.execute(getString(R.string.get_chatroomsAPI));

        //给每个ListView生成的TextViwe创建监听事件
        this.chatroomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChatroomBean chatroomBean = (ChatroomBean)chatroomListView.getItemAtPosition(position);
                //System.out.println(chatroomBean.getId());
                int chatroom_id = chatroomBean.getId();
                String chatroom_name = chatroomBean.getName();
                //System.out.println(chatroom_id+chatroom_name);

                //传递所选择chatroom信息给chatActivity
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("id",chatroom_id);
                intent.putExtra("name",chatroom_name);

                startActivity(intent);
            }
        });
    }

}
