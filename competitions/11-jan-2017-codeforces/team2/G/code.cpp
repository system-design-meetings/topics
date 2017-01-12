#include <algorithm>
#include <vector>
#include <iostream>
#include <string>
#include <queue>
#include <map>
#include <fstream>
#include <ostream>
#include <set>

using namespace std;

typedef long long ll;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);

	ifstream infile("suffixgame.in");
	ofstream outfile("suffixgame.out");

	int n;

	infile >> n;

	ll total = 0;
	int prev = 0;

	for(int i = 0; i < n; i++){
		int next;
		infile >> next;
		total += (next-prev);
		prev = next;
	}

	total -= n;

	if (total % 2 == 0) {
		outfile << "Second" << endl;
	} else {
		outfile << "First" << endl;
	}

	return 0;
}
 