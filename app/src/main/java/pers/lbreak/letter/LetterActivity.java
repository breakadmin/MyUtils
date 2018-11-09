package pers.lbreak.letter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import pers.lbreak.BR;
import pers.lbreak.R;
import pers.lbreak.databinding.ActivityLetterBinding;
import pers.lbreak.myutils.adapter.RvAdapter;
import pers.lbreak.myutils.rv.LetterItemDecoration;

public class LetterActivity extends AppCompatActivity {
    ActivityLetterBinding binding;
    List<Contact> list = new ArrayList<Contact>();
    private Map<Integer, String> Titles = new HashMap<>();
  List<Letter> d = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding= DataBindingUtil. setContentView(this, R.layout.activity_letter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);

//设置增加或删除条目的动画
      binding.rv.setItemAnimator(new DefaultItemAnimator());


        Contact contact1 = new Contact();
        contact1.setName_ch("张三");
        contact1.setAccount("1");
        list.add(contact1);
        Contact contact2 = new Contact();
        contact2.setName_ch("李四");
        contact2.setAccount("2");
        list.add(contact2);
        Contact contact4 = new Contact();
        contact4.setName_ch("王五");
        contact4.setAccount("111");
        list.add(contact4);
        Contact contact5 = new Contact();
        contact5.setName_ch("赵钱");
        contact5.setAccount("1211");
        list.add(contact5);
        Contact contact6 = new Contact();
        contact6.setName_ch("宁波");
        contact6.setAccount("111111");
        list.add(contact6);

        Contact contact7 = new Contact();
        contact7.setName_ch("陈豪");
        contact7.setAccount("123");
        list.add(contact7);

        Contact contact8 = new Contact();
        contact8.setName_ch("百合");
        contact8.setAccount("123");
        list.add(contact8);

        Contact contact11 = new Contact();
        contact11.setName_ch("break");
        contact11.setAccount("123");
        list.add(contact11);
        Contact contact12 = new Contact();
        contact12.setName_ch("刘云");
        contact12.setAccount("123");
        list.add(contact12);

        Contact contact22 = new Contact();
        contact22.setName_ch("李娜");
        contact22.setAccount("123");
        list.add(contact22);

        Contact contact32 = new Contact();
        contact32.setName_ch("王森");
        contact32.setAccount("123");
        list.add(contact32);

        Contact contact42 = new Contact();
        contact42.setName_ch("王贤");
        contact42.setAccount("123");
        list.add(contact42);

        Contact contact9 = new Contact();
        contact9.setName_ch("王杰");
        contact1.setAccount("123");
        list.add(contact9);


        //根据姓名首字母排序
        Collections.sort(list,new Comparator<Contact>(){
            Comparator collator =  Collator.getInstance(Locale.CHINA);
            public int compare(Contact o1, Contact o2) {
                return collator.compare(((Contact)o1).getName_en(),((Contact)o2).getName_en()) ;
            }
        });

      binding.rv.setAdapter(new RvAdapter<Contact,Item>(R.layout.contact_item,list) {


        @Override
        public Item createViewHolder(View view) {
          return new Item(view);
        }

        @Override
        public void bindViewHolder(Item holder, int position, Contact contact) {
          holder.setData(BR.data,contact);
        }


      });//设置Adapter


        //标题
        Titles.put(0, list.get(0).getName_en().charAt(0)+"");
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getName_en().charAt(0)!=list.get(i - 1).getName_en().charAt(0)) {
                Titles.put(i, list.get(i).getName_en().charAt(0)+"");
            }

        }

        LetterItemDecoration itemDecoration = new LetterItemDecoration(true,this)
                .setTitles(Titles)
                .setText(15, Color.RED)
                .setDivider(1,0,70,Color.BLUE)
                .setBg(30,getResources().getColor(R.color.titleBgColor));
      binding.rv.addItemDecoration(itemDecoration);
//      setSideBar();
    }
//  /**
//   * 设置索引条
//   */
//  public void setSideBar(){
//    Letter letter = new Letter();
//    letter.setHover(true);
//    letter.setLetter("#");
//    d.add(letter);
//
//    for (Map.Entry<Integer, String> entry : Titles.entrySet()) {
//      Letter l = new Letter();
//      l.setLetter(entry.getValue());
//      d.add(l);
//    }
//
//    myAdapter = new MyAdapter(this, d);
//    IndexList.setAdapter(myAdapter);
//
//    binding.IndexList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        d.get(i).setHover(!d.get(i).isHover);
//        int index=0;
//        for (int x=0;x<d.size();x++){
//          if (d.get(x).isHover()==true){
//            index++;
//            if (x!=i){
//              d.get(x).setHover(!d.get(x).isHover);
//            }
//
//          }
//        }
//        if (index>0){
//          binding.IndexList.getAdapter().notifyAll();//更新索引位置
//        }
//
//        //跳转到指定位置
//        for (Map.Entry<Integer, String> entry : Titles.entrySet()) {
//          if (d.get(i).getLetter().equals(entry.getValue())){
//            rv.scrollToPosition(entry.getKey());
//            break;
//          }
//        }
//
//      }
//    });
//  }

}
