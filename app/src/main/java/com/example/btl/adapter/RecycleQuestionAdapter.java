package com.example.btl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.R;
import com.example.btl.dal.SQLiteHelper;
import com.example.btl.model.Answer;
import com.example.btl.model.Question;

import java.util.ArrayList;
import java.util.List;

public class RecycleQuestionAdapter extends RecyclerView.Adapter<RecycleQuestionAdapter.QuestionViewHolder> {
    ItemQuestionListener itemQuestionListener;
    List<Question> list;
    SQLiteHelper db ;

    public RecycleQuestionAdapter() {
        list = new ArrayList<>();
    }

    public RecycleQuestionAdapter(List<Question> list, SQLiteHelper db) {
        this.list = list;
        this.db = db;
    }

    public void setItemQuestionListener(ItemQuestionListener itemQuestionListener) {
        this.itemQuestionListener = itemQuestionListener;
    }

    public List<Question> getListQues(){
        return list;
    }
    public Question getQues(int position){
        return list.get(position);
    }

    public void setListQues(List<Question> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ques,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = list.get(position);
        holder.tvNumber.setText(String.valueOf(question.getQuestion_id())); // Chuyển đổi số nguyên sang chuỗi
        holder.tvContent.setText(question.getQuestion_content());
        String option = getAnswerText(question);
        holder.tvAnswer.setText(option);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNumber,tvContent,tvAnswer;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.item_ques_number);
            tvContent = itemView.findViewById(R.id.item_ques_content);;
            tvAnswer = itemView.findViewById(R.id.item_ques_answer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemQuestionListener!=null){
                itemQuestionListener.onItemQuestionClick(v,getAdapterPosition());
            }
        }
    }
    public interface ItemQuestionListener{
        void onItemQuestionClick(View view, int position);
    }

    private String getAnswerText(Question question) {
        String answerText = "";
        Answer answer = db.getAnswerByQuestion(question);
        if (answer.getDapan()==1) {
            answerText = answer.getOption1();
        }else  if (answer.getDapan()==2) {
            answerText = answer.getOption2();
        }
        else  if (answer.getDapan()==3) {
            answerText = answer.getOption3();
        }
        else  if (answer.getDapan()==4) {
            answerText = answer.getOption4();
        }
        return answerText;
    }
}

