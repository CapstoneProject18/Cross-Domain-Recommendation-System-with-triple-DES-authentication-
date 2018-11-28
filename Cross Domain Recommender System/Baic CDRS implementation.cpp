#include <iostream>
#include <string>
#include <algorithm>
#include <time.h>
using namespace std;
struct DataSet{
    string book;
    string movie;
    int crime,drama,mystery,thriller,romance,scifi,adventure,fantasy;
    double movierating;
    double bookrating;
    double rating;
};
void print(DataSet a[], int pos)
{
    cout<<"Movie: ";
    cout<<a[pos].movie<<endl;
    cout<<"Movie Genres: ";
    if(a[pos].crime==1)
        cout<<"Crime, ";
    if(a[pos].drama==1)
        cout<<"Drama, ";
    if(a[pos].mystery==1)
        cout<<"Mystery, ";
    if(a[pos].thriller==1)
        cout<<"Thriller, ";
    if(a[pos].romance==1)
        cout<<"Romance, ";
    if(a[pos].scifi==1)
        cout<<"Sci-Fi, ";
    if(a[pos].adventure==1)
        cout<<"Adventure, ";
    if(a[pos].fantasy==1)
        cout<<"Fantasy, ";
    cout<<endl;
    cout<<"Movie Rating: "<<a[pos].movierating<<endl;
}
int main()
{
	DataSet ds[15]={
	                    {"Angels & Demons","Angels & Demons",1,1,1,1,0,0,3.26,3.88,3.35},
	                    {"The Da Vinci Code","The Da Vinci Code",0,1,1,1,0,0,0,0,3.05,3.33,3.30},
    	                {"The Return of the King","The Lord of the Rings: The Return of the King",0,1,0,0,0,0,1,1,4.13,4.30,4.45},
    	                {"The Lincoln Lawyer","The Lincoln Lawyer",1,1,0,1,0,0,0,0,3.80,4.00,3.65},
    	                {"A Beautiful Mind","A Beautiful Mind",0,1,0,0,1,0,0,0,3.90,3.54,4.1},
    	                {"A Civil Action","A Civil Action",0,1,0,0,0,0,0,0,3.40,3.50,3.25},
    	                {"A Clockwork Orange","A Clockwork Orange",1,1,0,1,0,1,0,0,3.91,4.10,4.15},
    	                {"A Good Year","A Good Year",0,1,0,0,1,0,0,0,3.57,4.00,3.50},
    	                {"The Notebook","The Notebook",0,1,0,0,1,0,0,0,3.73,4.60,3.90},
    	                {"The Body","Stand By Me",0,1,0,0,0,0,1,0,4.03,4.00,4.05},
    	                {"The Time Traveler's Wife","The Time Traveler's Wife",0,1,0,0,1,1,0,0,3.74,4.50,3.55},
    	                {"Shutter Island","Shutter Island",0,1,1,1,0,0,0,0,3.86,4.05,4.05},
    	                {"Life of Pi","Life of Pi",0,1,0,0,0,0,1,0,3.97,4.20,3.95},
    	                {"Jane Eyre","Jane Eyre",0,1,0,0,1,0,0,0,3.60,4.50,4.20},
    	                {"Rock of Ages","Rock of Ages",0,1,0,0,0,0,0,0,3.06,3.15,2.95}
	                };
	string s;int pos=-999;
	cout<<"Which book did you read?"<<endl;
	getline (std::cin,s);
	for(int i=0;i<15;++i)
	{
	    if(s.compare(ds[i].book)==0)
            pos=i;
	}
    double rat=0.0;
    if(pos!=-999)
    {
        cout<<"First Movie: ";
        print(ds,pos);
        rat=ds[pos].movierating;
    }
    double arr[15];int p=-999;
	for(int i=0,x=0;i<15;++i)
        arr[x++]=ds[i].movierating;
    sort(arr,arr+15);
    for(int i=0;i<15;++i)
    {
        if(arr[i]==rat)
        {
            p=i;
            break;
        }
    }
    cout<<endl;cout<<endl;
    if(p!=-999)
    {
        cout<<"Other Relevant Movie Recommendations: "<<endl;
        for(int i=0;i<15;++i)
        {
            if(ds[i].movierating==arr[p-1])
                print(ds,i);
            if(ds[i].movierating==arr[p+1])
                print(ds,i);
        }
    }
    else
    {
        cout<<"There's no such movie adaptations from the entered book title!"<<endl;
        cout<<"However based on our ratings, you may like: "<<endl;
        srand(time(0));
        print(ds,rand()%15);
    }
	return 0;
}