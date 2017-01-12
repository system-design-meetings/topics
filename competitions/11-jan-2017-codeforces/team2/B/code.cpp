#include <algorithm>
#include <vector>
#include <iostream>
#include <string>
#include <queue>
#include <map>
#include <fstream>
#include <ostream>
#include <set>
#include <sstream>

using namespace std;

typedef long long ll;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);

	ifstream infile("basketball.in");
	ofstream outfile("basketball.out");

	map<string, int> scores;
	int n;
	infile >> n;
	for(int i = 0; i < n; i++){
		string name;
		infile >> name;
		scores[name] = 0;
	}

	int m;
	infile >> m;
	int x = 0;
	int y = 0;
	for(int i = 0; i < m; i++){
		string s, n;
		infile >> s >> n;
    	stringstream ss;
	    ss.str(s);
	    string item;
	    int newx = -1;
	    int newy = -1;
	    while (getline(ss, item, ':')) {
	        if (newx == -1) {
	        	newx = stoi(item);
	        } else {
	        	newy = stoi(item);
	        }
	    }
	    scores[n] += (newx-x)+(newy-y);
	    x = newx; y = newy;
	}
	int ans = -1;
	string person;
	for(auto k: scores){
		if (k.second > ans) {
			person = k.first;
			ans = k.second;
		}
	}
	outfile << person << " " << ans << endl;
	return 0;
}
